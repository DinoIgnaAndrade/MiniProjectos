from fastapi import APIRouter, Depends, HTTPException,status
from pydantic import BaseModel
#modulo de autenticacion
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm #el primero gestiona el usuario y la contraseña, y 
#el otro es la forma de enviarlo al backend para comprobar si si es usuario


router = APIRouter(prefix="/login",
                   tags=["login"], # los tag son para crear categoria de API especifica.
                   responses={404:{"Message":"No encontrado"}})

oauth2 = OAuth2PasswordBearer(tokenUrl="login")

'''uvicorn API.routers.basic_auth_user:app --reload'''

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
        "password": "123456"
    },
    "[Dack]":{
        "username": "[Dack]",
        "full_name": "Daniel Andrade",
        "email": "dinoignaandrade@gmail.com",
        "disabled": True,
        "password": "654321"
    }
}

def search_user_db(username: str):
    if username in users_db:
        return UserDB(**users_db[username])
    
def search_user(username: str):
    if username in users_db:
        return User(**users_db[username])
    
async def current_user(token: str = Depends(oauth2)):
    user = search_user(token)
    if not user:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED, # se importa un status para tener todo los codigos
            detail="La credencial de autenticacion no es correcto",
            headers = {"WWW-Authenticate:":"Bearer"}
        )
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
            detail="El usuario no es correcto"
        )

    user = search_user_db(form.username)
    if not form.password == user.password:
         raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail="La contraseña no es correcto"
        )
    
    return {"access_token": user.username ,"token_type":"bearer"}

@router.get("/users/me")
async def me(user: User = Depends(current_user)):
    return user
