from string import ascii_lowercase, whitespace, punctuation, digits
from collections import deque


class Caesar:
    newaz = deque(ascii_lowercase)
    newaz.rotate(-3)
    newaz = ''.join(newaz)
    cmap = str.maketrans(ascii_lowercase, newaz, whitespace + punctuation + digits)
    dmap = str.maketrans(newaz, ascii_lowercase)

    def encode(self, line):
        return line.lower().translate(self.cmap)

    def decode(self, line):
        return line.translate(self.dmap)


class Cipher:
    def encode(self, line):
        return line
