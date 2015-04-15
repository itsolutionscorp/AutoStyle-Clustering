__author__ = 'emiller42'
import string
import random


class Caesar:

    def __init__(self):
        self.c = Cipher('d')

    def encode(self, plain_text):
        return self.c.encode(plain_text)

    def decode(self, cipher_text):
        return self.c.decode(cipher_text)


class Cipher:

    def __init__(self, key=''.join(random.choice(string.ascii_lowercase) for x in range(0, 100))):
        if not all(char in string.ascii_lowercase for char in key):
            raise ArgumentError("key must be lowercase letters only")
        self.key = key

    def encode(self, plain_text):
        plain_text = plain_text.lower()
        output = ''
        for i in range(0, len(plain_text)):
            if plain_text[i] in string.ascii_lowercase:
                output += translate(plain_text[i], self.key[i % len(self.key)])
        return output

    def decode(self, cipher_text):
        output = ''
        for i in range(0, len(cipher_text)):
                output += translate(cipher_text[i], self.key[i % len(self.key)], True)
        return output


def find_letter(letter):
    return string.ascii_lowercase.find(letter)


def translate(letter, shift, reverse=False):
    if reverse:
        return string.ascii_lowercase[(find_letter(letter) - find_letter(shift)) % 26]
    else:
        return string.ascii_lowercase[(find_letter(letter) + find_letter(shift)) % 26]


class ArgumentError(Exception):
    pass
