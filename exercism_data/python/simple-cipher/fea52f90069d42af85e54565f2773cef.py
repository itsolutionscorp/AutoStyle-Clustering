import string
import random

class Cipher(object):
    def __init__(self, key = None):
        if key and not (key.islower() and key.isalpha):
            raise ValueError('Invalid Key')
        self.alphabet = list(string.ascii_lowercase)
        self.key = key or self.generate_key()

    def generate_key(self):
        return [random.SystemRandom().choice(self.alphabet) for x in range(100)]

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

class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d' * 100)
