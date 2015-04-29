from math import sqrt

def encode(string):
    message = char_string_maker(string)
    square_size = size(message)
    crypto_square = square_key(message, square_size)
    cipher = code(crypto_square)
    return cipher


def char_string_maker(string):
    string = string.lower()
    message = ''
    for char in string:
        if char.isalnum():
            message += char
    return message

def size(string):
    square_size = 1
    while square_size < len(string)/square_size:
        square_size += 1
    return square_size

def square_key(string, size):
    crypto_square = []
    for i in range(int(len(string)/size) + 1):
        crypto_square.append('')
        for j in range(size):
            if i*size + j+1 <= len(string):
                crypto_square[i] += string[(i)*size + j]
    return crypto_square

def code(square_key):
    cipher = ""
    if len(square_key)> 0:
        for i in range(len(square_key[0])):
            for j in range(len(square_key)):
                if i < len(square_key[j]):
                    cipher += square_key[j][i]
            if i < len(square_key[0]) - 1:
                cipher += ' '
    return cipher
