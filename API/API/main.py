from fastapi import FastAPI
from .routers import products,user,user_db,jwt_auth_users,basic_auth_user # se le agraga un punto porque esta dentro de una carpeta que esta dentro de la carpeta del proyecto.
from fastapi.staticfiles import StaticFiles

app = FastAPI()

#uvicorn API.main:app --reload / Asi se inicia el servidor dentro de la carpeta backed

#Routers
app.include_router(products.router)
app.include_router(user.router)
app.include_router(user_db.router)
app.include_router(jwt_auth_users.router)
app.include_router(basic_auth_user.router)

app.mount("/static",StaticFiles(directory='API'),name="static") #/static/static/images/python.jpg

@app.get("/")
async def root():
    return "Hola FastAPI"

@app.get("/url")
async def url():
    return { "message":"Hola FastAPI", "Imagen":"http://127.0.0.1:8000/static/static/images/python.jpg" }

