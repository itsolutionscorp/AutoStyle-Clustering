from string import ascii_lowercase
from itertools import izip, cycle

def shift_letter(c, amount):
    return chr((ord(c) - ord('a') + amount) % 26 + ord('a'))

class Caesar:

    @staticmethod
    def encode(msg, shift=3):
        msg = filter(lambda a: a in ascii_lowercase, msg.lower())
        return ''.join([shift_letter(c, shift) for c in msg])

    @staticmethod
    def decode(msg):
        return Caesar().encode(msg, -3)

class Cipher:

    def __init__(self, pattern='a'):
        self.pattern = pattern

    def encode(self, msg, decode=1):
        msg = filter(lambda a: a in ascii_lowercase, msg.lower())
        return ''.join([shift_letter(c, decode*(ord(d)-ord('a'))) for c, d in izip(msg, cycle(self.pattern))])

    def decode(self, msg):
        return self.encode(msg, -1)
