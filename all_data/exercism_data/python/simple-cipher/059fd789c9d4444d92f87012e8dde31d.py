from random import randint
from itertools import cycle


class Cipher:

    def __init__(self, key=None):
        if key:
            self.key = key
        else:
            self.key = [Cipher.from_26(randint(0, 25))
                        for i in range(100)]

    def encode(self, plain):
        return "".join(
            [Cipher.encode_char(x, y)
             for x, y
             in zip([c.lower() for c in plain if c.isalpha()],
                    cycle(self.key))])

    def decode(self, ciphered):
        return "".join(
            [Cipher.decode_char(x, y)
             for x, y
             in zip(ciphered,
                    cycle(self.key))])

    @staticmethod
    def from_26(n):
        return chr(n + ord('a'))

    @staticmethod
    def to_26(c):
        return ord(c) - ord('a')

    @staticmethod
    def decode_char(x, y):
        return Cipher.from_26((ord(x) - ord(y)) % 26)

    @staticmethod
    def encode_char(x, y):
        return Cipher.from_26((Cipher.to_26(x) + Cipher.to_26(y)) % 26)


class Caesar(Cipher):
    def __init__(self):
        super().__init__('d')
