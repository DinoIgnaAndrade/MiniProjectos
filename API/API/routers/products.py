from fastapi import APIRouter

router = APIRouter(prefix="/products",
                   tags=["products"], # los tag son para crear categoria de API especifica.
                   responses={404:{"Message":"No encontrado"}}) # con este prefijo ya no indico cada get

products_list = ["Producto 1","Producto 2","Producto 3"]

@router.get("/")
async def products():
    return ["Producto 1","Producto 2","Producto 3"]

@router.get("/{id}")
async def products(id: int):
    return products_list[id]