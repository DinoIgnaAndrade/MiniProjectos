import openai
import config
import typer
from rich import print
from rich.table import Table

def main():

    openai.api_key = config.api_key

    print("[bold green]ChatGPT API en Python[/bold green]")

    table = Table("Comando","Descripcion")
    table.add_row("exit","Salir de la aplicacion")
    table.add_row("new","Nueva conversacion")

    print(table)

    # Contexto del Asistente.
    context ={"role":"system",
               "content": "Eres un asistente muy util."}
    messages = [context] 

    while True:

        content = __prompt()

        if content == "new":
            print("[blue]Nueva Conversacion[/blue]")
            messages = [context]
            content = __prompt()

        #Con esto damos contexto al chat de la respuesta inicial
        messages.append({"role":"user", "content": content})

        response = openai.ChatCompletion.create(model="gpt-3.5-turbo",
                                                messages=messages)
        
        response_content = response.choices[0].message.content
        
        # Se guarda el flujo de interacción
        messages.append({"role":"assistant", "content": response_content})


        print(f"[red]> [blue]{response_content}[/blue]")

def __prompt() -> str:
    prompt = typer.prompt("\nCual es tu pregunta?")

    if prompt == "exit":
        exit = typer.confirm("Estas Seguro?")
        if exit:
            print("[bold green]Adios[/bold green]")
            raise typer.Abort() 

        return __prompt()       
    
    return prompt

if __name__== "__main__":
    typer.run(main)

