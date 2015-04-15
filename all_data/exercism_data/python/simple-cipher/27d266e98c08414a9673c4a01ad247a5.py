from string import ascii_lowercase, whitespace, punctuation, digits
from collections import deque
from itertools import cycle
from random import choice


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
    def __init__(self, key=''.join(choice(ascii_lowercase) for _ in range(120))):
        self.key = key

    def encode(self, line):
        return ''.join(chr((ord(c) + ord(k) - 2 * ord('a')) % 26 + ord('a')) for c, k in zip(line, cycle(self.key)))

    def decode(self, line):
        return ''.join(chr((ord(c) - ord(k)) % 26 + ord('a')) for c, k in zip(line, cycle(self.key)))
