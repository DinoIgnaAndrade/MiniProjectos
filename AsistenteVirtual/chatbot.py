import openai
import config


def main(text):

    openai.api_key = config.api_key

    print("ChatGPT API en Python")


    # Contexto del Asistente.
    context ={"role":"system",
               "content": "Eres un asistente muy util."}
    messages = [context] 

    while True:

        content = text

        if content == "nuevo":
            messages = [context]
            content = text

        #Con esto damos contexto al chat de la respuesta inicial
        messages.append({"role":"user", "content": content})

        response = openai.ChatCompletion.create(model="gpt-3.5-turbo",
                                                messages=messages)
        
        response_content = response.choices[0].message.content
        
        # Se guarda el flujo de interacción
        messages.append({"role":"assistant", "content": response_content})


        return(response_content)




