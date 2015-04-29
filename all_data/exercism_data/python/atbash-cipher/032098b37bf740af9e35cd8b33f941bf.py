__author__ = 'banarasitippa'

from string import maketrans,ascii_lowercase
atbash = maketrans(ascii_lowercase, ascii_lowercase[::-1])

def decode(ciphertext):
    # remove space and translate
    return ciphertext.replace(' ','').translate(atbash)

def encode(plaintext):

    #filter all characters except alphabets
    ciphertext = ''.join(c for c in plaintext.lower() if c.isalnum())

    #translate
    ciphertext = ciphertext.translate(atbash)

    # break into 4 char words and join with space
    return ''.join([ ciphertext[x:x+5]+' ' for x in range(0,len(ciphertext),5) ]).rstrip()
