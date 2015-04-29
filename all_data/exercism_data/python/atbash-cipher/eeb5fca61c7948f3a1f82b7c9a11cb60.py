alphabet = 'abcdefghijklmnopqrstuvwxyz'

def encode(message):
    message = message.lower()
    code = ''
    word_length_counter = 0
    for i in message:
        if i.isalnum():
            if word_length_counter == 5:
                code += ' '
                word_length_counter = 0
            if i.isalpha():
                code += alphabet[25 - alphabet.index(i)]
            else:
                code += i
            word_length_counter += 1
    return code

def decode(code):
    message = ''
    for i in code:
        if i.isalpha():
            message += alphabet[25 - alphabet.index(i)]
    return message
