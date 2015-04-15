from itertools import cycle
from random import randint

class Cipher:
    
    _ord_a = ord("a")
    _ord_z = ord("z")
    _ord_az = _ord_z - _ord_a + 1
    
    def __init__(self, key = None):
        if key and not (key.isalpha() and key.islower()):
            raise ValueError("Invalid key")
        self.key = key or self._generate_key()
         
    def encode(self, msg):
        return "".join(map(self._encode_char, msg, cycle(self.key)))

    def decode(self, emsg):
        return "".join(map(self._decode_char, emsg, cycle(self.key)))

    @staticmethod
    def _generate_key():
        return "".join(chr(randint(Cipher._ord_a, Cipher._ord_z)) for _ in range(100))
        
        
    def _encode_char(self, c, k):
        if not c.isalpha():
            return ""
        enc = ord(c.lower()) + ord(k) - self._ord_a
        return (enc > self._ord_z) and chr(enc - self._ord_az) or chr(enc)
    
    def _decode_char(self, c, k):
        dec = ord(c) - ord(k) + self._ord_a
        return (dec < self._ord_a) and chr(dec + self._ord_az) or chr(dec)


class Caesar(Cipher):
    def __init__(self):
        super().__init__("d")
