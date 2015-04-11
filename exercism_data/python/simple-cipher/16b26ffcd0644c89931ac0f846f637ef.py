import random


def rotate_char(ch, dist, backward=False):

    if not ch.isalpha():
        # skip unwanted characters
        return ''

    if type(dist) == str:
        # allow both int and str distances
        dist = ord(dist) - 97

    if backward:
        # encoding is forward
        dist = 0 - dist

    ret = ord(ch.lower()) + dist

    while ret > ord('z'):
        # for forward
        ret -= 26

    while ret < ord('a'):
        # for backward
        ret += 26

    return chr(ret)


class Caesar(object):

    @classmethod
    def recode(cls, msg, decode=False):
        # Might as well condense, no?
        return ''.join(rotate_char(ch, 'd', decode) for ch in msg)

    @classmethod
    def encode(cls, msg):
        return Caesar.recode(msg, False)

    @classmethod
    def decode(cls, msg):
        return Caesar.recode(msg, True)


class Cipher(object):
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    # Importing string seems unnecessary here

    def __init__(self, key=None):
        if not key:
            key = ''.join(random.choice(self.alphabet) for _ in range(30))
        self.key = key

    def recode(self, msg, decode=False):
        # Might as well condense, no?
        self.key = (self.key * (round(len(msg) / len(self.key)) + 1))
        # Pad the key with the key
        return ''.join(rotate_char(msg[i], self.key[i], decode)
                       for i in range(len(msg)))

    def encode(self, msg):
        return self.recode(msg, False)

    def decode(self, msg):
        return self.recode(msg, True)
