import string

plainchars = 'abcdefghijklmnopqrstuvwxyz'
codechars = 'zyxwvutsrqponmlkjihgfedcba'

def decode(cipher):
    table = string.maketrans(codechars, plainchars)
    return cipher.translate(table, ' ')

def encode(plain):
    table = string.maketrans(plainchars, codechars)
    unsplit =  plain.lower().translate(table, ' ,.')
    return ' '.join([unsplit[i:i+5] for i in range(0, len(unsplit), 5)])
