from fastapi import APIRouter,HTTPException
from pydantic import BaseModel
# El Base model da la capacidad de crear una entidad.

"""uvicorn API.routers.user:router --reload"""

#Entidad user
class User(BaseModel):
    id: int
    name: str
    surname: str
    age: int

users_list = [User(id=1, name="Dino",surname="Andrade",age=35),
              User(id=2, name="Fiamma",surname="Andrade",age=2),
              User(id=3, name="Susana",surname="Fernandez",age=3)]

router = APIRouter(prefix="/user",
                   tags=["user"], # los tag son para crear categoria de API especifica.
                   responses={404:{"Message":"No encontrado"}})

@router.get("/json")
async def usersjson():
    return [{"Name": "Dino", "Surname" : "Andrade" , "age": 35},
            {"Name": "Fiamma", "Surname" : "Andrade" , "age": 21},
            {"Name": "Susana", "Surname" : "Fernandez" , "age": 66},]

@router.get("/")
async def users():
    return users_list

#path
@router.get("/{id}")
async def user(id: int):
    users = filter(lambda user: user.id == id, users_list)
#    return list(users)[0]
    try:
        return list(users)[0]
    except:
        return {"error":"No se a encotrado el usuario"}

#query    
#@router.get("/userquery/") # en este caso se escribe el userquery/?id=1
#async def user(id: int):
    #users = filter(lambda user: user.id == id, users_list)
#    return list(users)[0]
    #try:
        #return list(users)[0]
    #except:
        #return {"error":"No se a encotrado el usuario"}

# Segunda variable mas facil.
@router.get("/query") # en este caso se escribe el userquery/?id=1
async def user(id: int):
    return search_user(id)



@router.post("/add",response_model=User, status_code=201) # crea un nuevo dato, configuramos el codigo de estado.# El response, te dice lo que devuelve.
async def user(user:User):
    if type(search_user(user.id)) == User:
        raise HTTPException(status_code=204, detail="El usuario ya existe") #crea un except de manera manual.
        #return HTTPException(status_code=204, detail="El usuario ya existe")
    
    users_list.routerend(user)
    return user
    
@router.put("/put/")# Actualiza datos
async def user(user:User):

    found = False

    for index,save_user in enumerate(users_list):
        if save_user.id == user.id:
            users_list[index] =user
            found = True

    if not found:
        return {"error":"No se a actualizado el usuario"}
    else:
        return user
    
@router.delete("/delete/{id}")
async def user(id:int):

    found = False

    for index,save_user in enumerate(users_list):
        if save_user.id == id:
            del users_list[index]
            found = True

    if not found: 
        return {"error":"No se a eliminado el usuario"}
    

def search_user(id: int):
    users = filter(lambda user: user.id == id, users_list)
    try:
        return list(users)[0]
    except:
        return {"error":"No se ha encotrado el usuario"}

