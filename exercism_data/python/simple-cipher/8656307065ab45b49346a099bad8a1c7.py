from itertools import cycle, izip

__author__ = 'jimblackler'


def shift_char(x, by):
    return chr((ord(x) - ord('a') + by) % 26 + ord('a'))


class Cipher(object):

    def __init__(self, key='d'):
        self.key = key

    def encode(self, message, direction=1):
        message = ''.join([x.lower() for x in message if x.isalpha()])
        return ''.join([shift_char(x, (ord(d) - ord('a')) * direction) for x, d in izip(message, cycle(self.key))])

    def decode(self, message):
        return self.encode(message, -1)


class Caesar(Cipher):
    pass
