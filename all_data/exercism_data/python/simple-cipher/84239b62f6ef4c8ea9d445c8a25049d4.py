import string
import itertools

FIRST = ord('a')
SIZE = len(string.lowercase)

class Caesar(object):
    def __init__(self, key=None):
        if key:
            self.key = [ord(k) - FIRST for k in key.lower()]
        else:
            self.key = [3]

    def _do_encode(self, text, direction=1):
        result = []
        for ch, shift in zip(text.lower(), itertools.cycle(self.key)):
            if ch not in string.lowercase:
                continue
            shift *= direction
            n = (ord(ch) - FIRST + shift) % SIZE
            while n < 0: 
                n += SIZE
            result.append(chr(n + FIRST))
        return ''.join(result)

    def encode(self, text):
        return self._do_encode(text, 1)

    def decode(self, text):
        return self._do_encode(text, -1)

Cipher = Caesar
