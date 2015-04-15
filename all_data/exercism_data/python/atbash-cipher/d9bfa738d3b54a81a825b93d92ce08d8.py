plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'

def decode(message):
    decoded_message = ''
    for character in message:
        if character.isalpha():
            decoded_message += plain[cipher.index(character.lower())]
    return decoded_message

def encode(message):
    encoded_message = ''
    for character in message:
        if character.isalpha():
            encoded_message += cipher[plain.index(character.lower())]
    return encoded_message
