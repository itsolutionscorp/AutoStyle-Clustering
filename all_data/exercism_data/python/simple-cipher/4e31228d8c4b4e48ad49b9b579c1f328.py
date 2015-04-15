import string
from random import choice
from itertools import cycle, izip


class Cipher(object):
    def __init__(self, key=None):
        super(Cipher, self).__init__()
        if not key:
            key = ''.join(choice(string.ascii_lowercase) for i in xrange(0, 100))

        if not all(c.islower() and c.isalpha() for c in key):
            raise ValueError("Invalid key!")

        self.key = key
        self._offsets = tuple(ord(c) - ord('a') for c in self.key)

    def _code(self, text, method=1):
        a = ord('a')
        chars = (ord(c.lower()) for c in text if c.isalpha())
        offsets = (o * method for o in cycle(self._offsets))
        for c, o in izip(chars, offsets):
            yield chr(a + (c - a + o + 26) % 26)

    def encode(self, text):
        return ''.join(self._code(text, 1))

    def decode(self, text):
        return ''.join(self._code(text, -1))


class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d')
