from itertools import cycle
from string import ascii_lowercase as letters
from random import choice
import re
pattern = re.compile('[^a-zA-Z]')


class Cipher(object):

    def __init__(self, key=None):
        if key is None:
            key = [choice(letters) for i in range(100)]
        self.key = key

    def encode(self, message):
        message = re.sub(pattern, '', message.lower())
        return ''.join(letters[((letters.index(i) + letters.index(j)) % len(letters))] for i, j in zip(message, cycle(self.key)))

    def decode(self, message):
        return ''.join(letters[((letters.index(i) - letters.index(j)) % len(letters))] for i, j in zip(message, cycle(self.key)))


class Caesar(Cipher):

    def __init__(self):
        super(Caesar, self).__init__('d')
