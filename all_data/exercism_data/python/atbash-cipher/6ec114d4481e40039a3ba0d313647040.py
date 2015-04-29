import string

trans = string.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')

grp = 5

other_ch = string.punctuation + string.whitespace

def encode(plain):
    cat = plain.lower().translate(trans, other_ch)
    cipher_list = [cat[i: i + grp] for i in range(0, len(cat), grp)]
    cipher = " ".join(cipher_list)
    return cipher

def decode(cipher):
    return cipher.lower().translate(trans, " ")
