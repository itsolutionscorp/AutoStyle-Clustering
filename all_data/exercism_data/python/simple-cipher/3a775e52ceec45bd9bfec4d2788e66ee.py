import string
import random

class Caesar():
    def __init__(self):
        self.alphabet = list(string.ascii_lowercase)

    def encode(self, message):
        return Cipher('d'*len(message)).encode(message)

    def decode(self, message):
        return Cipher('d'*len(message)).decode(message)

class Cipher():
    def __init__(self, key = None):
        self.alphabet = list(string.ascii_lowercase)
        self.key = key
        if self.key == None:
            self.key = [random.SystemRandom().choice(self.alphabet) for x in range(100)]

    def code(self, message, multiplier = 1):
        cipher = []
        for i, letter in enumerate(message.lower()):
            if letter in self.alphabet:
                shift = 0 if i >= len(self.key) else self.alphabet.index(self.key[i])
                origin = self.alphabet.index(letter)
                cipher.append(self.alphabet[(origin + shift * multiplier) % len(self.alphabet)])
        return ''.join(cipher)

    def encode(self, message):
        return self.code(message)

    def decode(self, message):
        return self.code(message, -1)
