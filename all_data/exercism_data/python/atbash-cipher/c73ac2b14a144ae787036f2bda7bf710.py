import string

cipher_list = ['z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f',
               'e', 'd', 'c', 'b', 'a']
alphabet = cipher_list[::-1]


def encode(message):
    encoded_message = ''
    message = message.lower()
    message = message.translate(string.maketrans("", ""), string.punctuation)
    message = message.replace(" ", "")
    for letter in message:
        temp = encoded_message.replace(" ", "")
        if len(temp) % 5 == 0 and len(encoded_message) != 0:
            encoded_message += ' '
        if letter.isalpha():
            encoded_message += cipher_list[alphabet.index(letter)]
        else:
            encoded_message += letter
    return encoded_message


def decode(message):
    decoded_message = ''
    message = message.replace(" ", "")
    for letter in message:
        decoded_message += alphabet[cipher_list.index(letter)]
    return decoded_message
