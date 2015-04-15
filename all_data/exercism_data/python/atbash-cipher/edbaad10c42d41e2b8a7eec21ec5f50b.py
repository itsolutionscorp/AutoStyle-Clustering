import string

TABLE = str(" "*48 + "0123456789" + " "*39 + "zyxwvutsrqponmlkjihgfedcba" + " "*133)

def decode(text):
    text = text.lower().replace(' ', '')
    message = string.translate(text, TABLE, string.punctuation)
    return message

def encode(text):
    text = text.lower().replace(' ', '')
    message = string.translate(text, TABLE, string.punctuation)
    formattedMessage = ""
    for i in range(len(message)):
        if i % 5 == 0 and i != 0:
            formattedMessage += " "
        formattedMessage += message[i]
    return formattedMessage
