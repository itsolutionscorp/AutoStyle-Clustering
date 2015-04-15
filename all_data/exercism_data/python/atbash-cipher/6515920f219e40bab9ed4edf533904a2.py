__author__ = 'Flavio Miranda'

import re

_decipher = "abcdefghijklmnopqrstuvwxyz"
_cipher = "zyxwvutsrqponmlkjihgfedcba"


def decode(decipher):
    transtab = str.maketrans(_decipher, _cipher)
    decipher = decipher.replace(" ", "")
    ciphered = decipher.lower().translate(transtab)
    return "".join([str(ciphered[i]) for i in range(0, len(ciphered))])


def encode(cipher):
    regex = re.compile('[^a-z0-9]')
    cipher = regex.sub('', cipher.lower())
    transtab = str.maketrans(_cipher, _decipher)
    deciphered = cipher.translate(transtab)
    return (" ".join([deciphered[5*i:5*i+5] for i in range(0, int(len(deciphered)/5)+1)])).strip()
