from string import ascii_lowercase
import random

class Cipher:

    def __init__(self, key=None):
        if key:
            self.key = self._normalize(key)
        else:
            random.seed()
            self.key = ''.join(random.choice(ascii_lowercase)
                               for _ in range(100))


    def encode(self, text):
        result = ''
        key = self.key
        key *= len(self.key) * len(text) // len(self.key)
        for i, char in enumerate(self._normalize(text)):
            index = (ascii_lowercase.index(char) + ascii_lowercase.index(key[i])) % 26
            result += ascii_lowercase[index]
        return result


    def decode(self, text):
        result = ''
        key = self.key
        key *= len(self.key) * len(text) // len(self.key)
        for i, char in enumerate(self._normalize(text)):
            index = (ascii_lowercase.index(char) - ascii_lowercase.index(key[i])) % 26
            result += ascii_lowercase[index]
        return result


    def _normalize(self, text):
        return ''.join(c.lower() for c in text if c.isalpha())


class Caesar(Cipher):

    def __init__(self):
        super().__init__('d')
