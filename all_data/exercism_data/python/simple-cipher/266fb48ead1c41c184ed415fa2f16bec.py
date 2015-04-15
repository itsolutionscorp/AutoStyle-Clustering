import random
from itertools import izip_longest
from string import digits, lowercase, whitespace, punctuation

ORDA = ord('a')
ORDZ = ord('z')


class Caesar(object):
    IGNORED = set(digits + whitespace + punctuation)

    def __init__(self):
        self._key = 'd'

    def _convert(self, msg, func):
        msg = msg.lower()
        N = len(msg)
        fillchar = self._key[-1]
        return ''.join(func(s, k) for s, k in izip_longest(
            msg, self._key[:N], fillvalue=fillchar
        ) if s not in self.IGNORED)

    def encode(self, msg):
        return self._convert(msg, _cfwd)

    def decode(self, msg):
        return self._convert(msg, _crev)


class Cipher(Caesar):
    def __init__(self, key=None):
        self._key = key or self._genkey()

    def _genkey(self):
        return ''.join(random.choice(lowercase) for _ in range(100))


def _cfwd(c, k):
    shift = ord(c) + ord(k) - ORDA
    scorr = shift - ORDZ + ORDA - 1 if shift > ORDZ else shift
    return chr(scorr)


def _crev(c, k):
    shift = ord(c) - (ord(k) - ORDA)
    scorr = ORDZ + (shift - ORDA) + 1 if shift < ORDA else shift
    return chr(scorr)
