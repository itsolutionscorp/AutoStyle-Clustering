from string import ascii_lowercase
from random import choice
from itertools import cycle

def ascii_to_index(ch):
    return ord(ch) - ord('a')

def index_to_ascii(n):
    return chr(ord('a') + n)

def _rotate_ch(ch, shift):
    return index_to_ascii((ascii_to_index(ch) + shift)%26)

class Cipher:
    def __init__(self, key=None):
        self.key = key or ''.join(choice(ascii_lowercase) for _ in range(100))

    def _en_de_code(self, text, sign=1):
        text = ''.join(filter(str.isalpha, text.lower()))
        key = cycle(ascii_to_index(ch) for ch in self.key)
        return ''.join(_rotate_ch(ch, sign*k)
                       for ch, k in zip(text, key))

    def encode(self, plaintext):
        return self._en_de_code(plaintext)

    def decode(self, cyphertext):
        return self._en_de_code(cyphertext, sign=-1)

class Caesar:
    encode = Cipher('d').encode
    decode = Cipher('d').decode
