plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'

def decode(message):
    decoded_message = ''
    for character in message:
        if character.isalnum():
            decoded_message += plain[cipher.index(character.lower())]
    return decoded_message


def encode_block(message):
    encoded_message = ''
    for character in message:
        if character.isalpha():
            encoded_message += cipher[plain.index(character.lower())]
        else:
            encoded_message += character
    return encoded_message


def encode(message, block_size=5):
    encoded_message = ''
    compressed_message = ''.join(character for character in message if character.isalnum()) #remove all non alphanumeric characters
    position=0
    while position<len(compressed_message):
        encoded_message += encode_block(compressed_message[position:position+block_size])+' '
        position+=block_size
    return encoded_message[:-1]
