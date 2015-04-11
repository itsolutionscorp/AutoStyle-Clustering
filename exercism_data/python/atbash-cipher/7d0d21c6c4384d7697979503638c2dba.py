__author__ = 'banarasitippa'

from string import maketrans
plain =  'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'
trantab = maketrans(plain, cipher)

def decode(ciphertext):
    # remove space and translate
    return ciphertext.replace(' ','').translate(trantab)

def encode(plaintext):

    #filter all characters except alphabets
    ciphertext = ''.join(c for c in plaintext.lower() if c.isalnum())

    #translate
    ciphertext = ciphertext.translate(trantab)

    # break into 4 char words and join with space
    return ''.join([ ciphertext[x:x+5]+' ' for x in range(0,len(ciphertext),5) ]).rstrip()
