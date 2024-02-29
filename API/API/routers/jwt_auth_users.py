from fastapi import APIRouter, Depends, HTTPException,status
from pydantic import BaseModel
#modulo de autenticacion
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm #el primero gestiona el usuario y la contraseña, y 
#el otro es la forma de enviarlo al backend para comprobar si si es usuario
from jose import jwt,JWTError
from passlib.context import CryptContext
#encriptador
from datetime import datetime, timedelta

ALGORITHM = "HS256"
ACCESS_TOKEN_DURATION  = 1
SECRET = "ce60917c2b9abb45d69f89c114858e028443f44d9f80977b3b73a9c4221ee146"

router = APIRouter(prefix="/login2",
                   tags=["login2"], # los tag son para crear categoria de API especifica.
                   responses={404:{"Message":"No encontrado"}})

oauth2 = OAuth2PasswordBearer(tokenUrl="login")

crypt = CryptContext(schemes=["bcrypt"])

'''uvicorn API.routers.jwt_auth_users:app --reload'''

#Entidad user
class User(BaseModel):
    username: str
    full_name: str
    email: str
    disabled: bool

class UserDB(User):
    password: str

users_db = {
    "daynoxs":{
        "username": "daynoxs",
        "full_name": "Dino Andrade",
        "email": "dinoignaandrade@gmail.com",
        "disabled": False,
        "password": "$2a$12$wJW2yYXnagkkTQgwjSvkXex5o3C57Hefb2IkiZrp5.3n0Mr5dnY6W"
    },
    "[Dack]":{
        "username": "[Dack]",
        "full_name": "Daniel Andrade",
        "email": "dinoignaandrade@gmail.com",
        "disabled": True,
        "password": "$2a$12$NuaPFZRCSZYgKmUcuhFkFe/MRTr9Vv80enpacinE9HaYXpwRvGNJC"
    }
}

def search_user_db(username: str):
    if username in users_db:
        return UserDB(**users_db[username])
    
def search_user(username: str):
    if username in users_db:
        return User(**users_db[username])
    
async def auth_user(token: str = Depends(oauth2)):

    exception=HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED, # se importa un status para tener todo los codigos
            detail="La credencial de autenticacion no es correcto",
            headers = {"WWW-Authenticate:":"Bearer"}
        )

    try:
        username = jwt.decode(token, SECRET, algorithms=[ALGORITHM]).get("sub")
        if username is None:
            raise exception

    except JWTError:
           raise exception
    
    return search_user(username)

    
async def current_user(user: User = Depends(auth_user)):
    if user.disabled:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST, # se importa un status para tener todo los codigos
            detail="Usuario Inactivo"
        )
    return user

@router.post("/login")
async def login(form: OAuth2PasswordRequestForm = Depends()): #recibe datos y con el depends no necesita depender de nada

    user_db = users_db.get(form.username)
    if not user_db:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="La contraseña no es correcto"
        )

    user = search_user_db(form.username)    

    if not crypt.verify(form.password, user.password): # verifica la original con la encryptada
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="La contraseña no es correcto"
        )

    access_token = {"sub":user.username,
                    "exp":datetime.utcnow() + timedelta(minutes = ACCESS_TOKEN_DURATION)}

    return {"access_token": jwt.encode(access_token, 
                                       SECRET, 
                                       algorithm=ALGORITHM),
                                       "token_type":"bearer"
                                       }

@router.get("/users/me")
async def me(user: User = Depends(current_user)):
    return user
