__author__ = 'Eric'
import string
import random


class Caesar:

    def __init__(self):
        pass

    @staticmethod
    def encode(plain_text):
        c = Cipher('d' * len(plain_text))
        return c.encode(plain_text)

    @staticmethod
    def decode(cipher_text):
        c = Cipher('d' * len(cipher_text))
        return c.decode(cipher_text)


class Cipher:


    @staticmethod
    def find_letter(letter):
        return string.ascii_lowercase.find(letter)

    @staticmethod
    def translate(letter, shift, reverse):
        if reverse:
            return string.ascii_lowercase[(Cipher.find_letter(letter) - Cipher.find_letter(shift)) % 26]
        else:
            return string.ascii_lowercase[(Cipher.find_letter(letter) + Cipher.find_letter(shift)) % 26]

    def __init__(self, key=''.join(random.choice(string.ascii_lowercase) for x in range(0, 100))):
        if not all(char in string.ascii_lowercase for char in key):
            raise ArgumentError("key must be lowercase letters only")
        self.key = key

    def encode(self, plain_text):
        plain_text = plain_text.lower()
        output = ''
        for i in range(0, len(plain_text)):
            if plain_text[i] in string.ascii_lowercase:
                output += Cipher.translate(plain_text[i], self.key[i % len(self.key)], False)
        return output

    def decode(self, cipher_text):
        output = ''
        for i in range(0, len(cipher_text)):
                output += Cipher.translate(cipher_text[i], self.key[i % len(self.key)], True)
        return output


class ArgumentError(Exception):
    pass
