from collections import deque
from itertools import cycle
from string import ascii_lowercase

class Cipher:
    def __init__(self, key='d'):
        self.key = key
        self._abc = list(ascii_lowercase)
    def encode(self, msg):
        return(self._transform(msg, lambda k: self._create_lookup(k)))
    def decode(self, msg):
        return(self._transform(msg, lambda k: self._create_lookup(k, reverse=True)))
    def _transform(self, msg, lt_fun):
        r = []
        for p, k in zip(self._strip_msg(msg), cycle(self.key)):
            r.append(lt_fun(k)[self._abc.index(p)])
        return(''.join(r))
    def _strip_msg(self, s):
        return(''.join(filter(lambda c: c in ascii_lowercase, s.lower())))
    def _create_lookup(self, offset, reverse=False):
        lookup = deque(self._abc)
        shift_distance = self._abc.index(offset)
        if not reverse:
            shift_distance *= -1
        lookup.rotate(shift_distance)
        return(lookup)

class Caesar(Cipher):
    pass
