#!/usr/bin/python
import string
from collections import deque
from random import randint
class Caesar():
    def encode(self, phrase, flag = True):
        cipher = deque(string.lowercase)
        cipher.rotate(-3)
        if flag:
            letter_mapping = dict(zip(string.lowercase, cipher))
        else:
            letter_mapping = dict(zip(cipher, string.lowercase))
        return "".join([letter_mapping[c] for c in phrase.lower() if c in string.lowercase])

    def decode(self, phrase):
        return self.encode(phrase, False)

class Cipher():
    a = ord("a")

    def __init__(self, key = None):
        self.key = deque(self.generate_key(key))
    
    def encode(self, phrase, decoding = False):
        result = ""
        for char in phrase:
            mapping = self.key[0] * (-1 if decoding else 1)
            new_char = chr(((26 + (ord(char) - self.a) + (mapping)) % 26) + self.a)
            result += new_char
            self.key.rotate(-1)
        self.key.rotate(len(phrase))
        return result

    def generate_key(self, key):
        if key is None:
            return [randint(0, 25) for i in xrange(100)]
        else:
            return [ord(c) - self.a for c in key]

    def decode(self, phrase):
        return self.encode(phrase, True)
