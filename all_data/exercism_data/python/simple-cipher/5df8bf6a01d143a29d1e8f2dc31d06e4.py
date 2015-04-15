from string import ascii_lowercase, digits, punctuation, whitespace
from itertools import cycle

class Cipher:
    def __init__(self, key='d'):
        self.key = key

    @staticmethod
    def _shift(m, k):
        a = ord('a')
        m = ord(m) - a
        k = ord(k) - a
        return chr(((m + k) % 26) + a)

    @staticmethod
    def _unshift(m, k):
        a = ord('a')
        m = ord(m) - a
        k = ord(k) - a
        return chr(((m - k) % 26) + a)

    def encode(self, message):
        lower_ascii_message = filter(lambda x: x in ascii_lowercase, message.lower())
        return ''.join(Cipher._shift(m, k)
                       for m, k in zip(lower_ascii_message, cycle(self.key)))

    def decode(self, ciphertext):
         return ''.join(Cipher._unshift(m, k) for m, k in zip(ciphertext, cycle(self.key)))

class Caesar(Cipher):
    def __init__(self):
        self.key = 'd'
