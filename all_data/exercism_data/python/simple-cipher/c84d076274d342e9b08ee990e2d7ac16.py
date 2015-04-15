from string import ascii_lowercase
from collections import deque


class Caesar:
    newaz = deque(ascii_lowercase)
    newaz.rotate(-3)
    cmap = str.maketrans(ascii_lowercase, ''.join(newaz))

    def encode(self, line):
        return line.translate(self.cmap)


class Cipher:
    def encode(self, line):
        return line
