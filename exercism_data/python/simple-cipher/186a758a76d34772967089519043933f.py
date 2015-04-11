from random import choice
from string import ascii_lowercase
from re import sub
from itertools import izip

class Cipher(object):

    maxlen = 100 # max plaintext length

    def __init__(self,key=None):
        if not key:
            self.key = ''.join(choice(ascii_lowercase) for count in xrange(Cipher.maxlen))
        elif not key.isalpha() or not key.islower():
            raise ValueError('Key must be lowercase and alphabetic.')
        else:
            self.key = key

    def encode(self,pt):
        if len(pt) > Cipher.maxlen:
            raise ValueError('Text length must be less than ' + str(Cipher.maxlen))

        # Remove all non-alphabet, and shift to lowercase
        pt_clean = sub('[^a-z]+', '', pt.lower())
        return ''.join(_shift(c,k) for c,k in izip(pt_clean,self.key)) + pt_clean[len(self.key):]

    def decode(self,ct):
        if len(ct) > Cipher.maxlen:
            raise ValueError('Text length must be less than ' + str(Cipher.maxlen))
        elif not ct.isalpha() or not ct.islower():
            raise ValueError('Invalid ciphertext - must be lowercase and alphabetic.')
        else:            
            return ''.join(_shift(c,k,True) for c,k in izip(ct,self.key)) + ct[len(self.key):]


class Caesar(Cipher):

    def __init__(self):
        self.key = 'd'*Cipher.maxlen


def _shift(a,b,inv=False):
    '''Shift char a by key b.
    inv=True for the inverse function.'''
    return chr((ord(a)-97 + (ord(b) - 97)*(-1 if inv else 1)) % 26 + 97)
