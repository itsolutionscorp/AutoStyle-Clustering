import random
from itertools import zip_longest

ALPHABET = 'abcdefghijklmnopqrstuvwxyz'

def rotate_char(ch1, ch2, backward=False):

    if not ch1.isalpha():
        return ''

    ch2 = ord(ch2) - 97

    if backward:
        ch2 = 0 - ch2

    ret = ord(ch1.lower()) + ch2

    while ret > ord('z'):
        ret -= 26

    while ret < ord('a'):
        ret += 26

    return chr(ret)


class Cipher(object):

    def __init__(self, key=None):
        if not key:
            key = ''.join(random.choice(ALPHABET) for _ in range(30))
        self.key = key

    def recode(self, msg, decode=False):
        return ''.join(rotate_char(a, b, decode) for a, b in
                       zip_longest(msg, self.key[:len(msg)], fillvalue=self.key[0]))

    def encode(self, msg):
        return self.recode(msg, False)

    def decode(self, msg):
        return self.recode(msg, True)


class Caesar(Cipher):

    def __init__(self):
        super(Caesar, self).__init__('d')
