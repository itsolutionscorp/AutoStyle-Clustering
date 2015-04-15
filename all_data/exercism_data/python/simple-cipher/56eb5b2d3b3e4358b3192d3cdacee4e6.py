from random import choice
from string import ascii_lowercase
from re import sub
from itertools import izip, cycle

class Cipher(object):

    defaultkeylen = 100

    def __init__(self,key=None):
        if not key:
            self.key = ''.join(choice(ascii_lowercase) for count in xrange(Cipher.defaultkeylen))
        elif not key.isalpha() or not key.islower():
            raise ValueError('Key must be lowercase and alphabetic.')
        else:
            self.key = key

    def encode(self,pt):
        # Remove all non-alphabet, and shift to lowercase
        pt_clean = sub('[^a-z]+', '', pt.lower())
        return ''.join(_shift(c,k) for c,k in izip(pt_clean,cycle(self.key)))

    def decode(self,ct):
        if not ct.isalpha() or not ct.islower():
            raise ValueError('Invalid ciphertext - must be lowercase and alphabetic.')
        else:            
            return ''.join(_shift(c,k,True) for c,k in izip(ct,cycle(self.key)))


class Caesar(Cipher):

    def __init__(self):
        self.key = 'd'


def _shift(a,b,inv=False):
    '''Shift char a by key b.
    inv=True for the inverse function.'''
    return chr((ord(a)-97 + (ord(b)-97)*(-1 if inv else 1)) % 26 + 97)
