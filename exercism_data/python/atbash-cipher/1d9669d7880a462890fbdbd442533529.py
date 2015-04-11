key = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
no_convert = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

def encode(plain_text):
    cipher_text = ''
    for char in plain_text.lower():
        if char in key: 
           cipher_text += key[25 - key.index(char)]
        elif char in no_convert:
            cipher_text += char
    return ' '.join([cipher_text[i:i+5] for i in range(0, len(cipher_text), 5)])

def decode(cipher_text):
    return ''.join([key[25 - key.index(char)] for char in cipher_text.replace(' ', '')])
