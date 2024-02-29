from pymongo import MongoClient

# db_client = MongoClient().local /Cuando se pone el .local ya no hace falta ponerlo en el resto de los ficheros

#base de datos remota
db_client = MongoClient(
    "mongodb+srv://dinoignaandrade:gkjv44g1k7ZLFX3X@cluster0.6so5cot.mongodb.net/?retryWrites=true&w=majority").test