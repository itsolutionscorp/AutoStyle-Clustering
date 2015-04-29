import string
import random
import itertools


def rotatechar(ch, n):
    o = ord(ch) + n
    if o > ord('z'):
        o = ord('a') - ord('z') + o - 1
    elif o < ord('a'):
        o = ord('z') - ord('a') + o + 1

    return o


def clean(s):
    s = s.lower()
    return ''.join(ch for ch in s if ch in string.ascii_lowercase)


class Caesar:

    def __init__(self):
        pass

    def rotate(self, text, n):
        result = []

        for ch in text:
            o = rotatechar(ch, n)
            result.append(chr(o))

        return ''.join(result)

    def encode(self, plaintext):
        return self.rotate(clean(plaintext), 3)

    def decode(self, ciphertext):
        return self.rotate(ciphertext, -3)


class Cipher:

    def __init__(self, key=None):
        if key is None:
            self.key = self.random_key()
        else:
            self.key = key

    def random_key(self):
        return ''.join(random.choice([string.ascii_lowercase for i in range(100)]))

    def rotate(self, text, reverse=False):
        result = []

        for k, ch in zip(itertools.cycle(self.key), text):
            n = ord(k) - ord('a')
            if reverse:
                n = -n
            o = rotatechar(ch, n)
            result.append(chr(o))

        return ''.join(result)

    def encode(self, plaintext):
        return self.rotate(plaintext, False)

    def decode(self, ciphertext):
        return self.rotate(ciphertext, True)
