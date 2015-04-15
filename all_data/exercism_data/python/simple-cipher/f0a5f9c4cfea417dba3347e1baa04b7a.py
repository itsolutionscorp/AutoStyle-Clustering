from collections import deque
from itertools import cycle
from string import ascii_lowercase
from random import choice

ABC = list(ascii_lowercase)

class Cipher:
    def __init__(self, key=None):
        if not key:
            self.key = ''.join([choice(ABC) for count in range(128)])
        else:
            self.key = key
    def encode(self, msg):
        return(self._transform(msg, lambda k: self._create_lookup(k)))
    def decode(self, msg):
        return(self._transform(msg, lambda k: self._create_lookup(k, reverse=True)))
    def _transform(self, msg, lt_fun):
        r = []
        for p, k in zip(self._strip_msg(msg), cycle(self.key)):
            r.append(lt_fun(k)[ABC.index(p)])
        return(''.join(r))
    def _strip_msg(self, s):
        return(''.join(filter(lambda c: c in ascii_lowercase, s.lower())))
    def _create_lookup(self, offset, reverse=False):
        lookup = deque(ABC)
        shift_distance = ABC.index(offset)
        if not reverse:
            shift_distance *= -1
        lookup.rotate(shift_distance)
        return(lookup)

class Caesar(Cipher):
    def __init__(self):
        self.key = 'd'
