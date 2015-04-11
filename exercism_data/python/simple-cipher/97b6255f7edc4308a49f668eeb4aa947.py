import random

ord_a = ord('a')

def shift(c, shiftc, reverse=False):
    dist = ord(shiftc) - ord_a
    if reverse:
        dist *= -1
            
    return chr(ord_a + (ord(c) - ord_a + dist) % 26)

def sanitize(plaintext):
    return ''.join(c for c in plaintext if c.isalpha()).lower()

class Cipher(object):
    def __init__(self, key=None):
        if not key:
            key = ''.join(chr(random.randint(ord('a'), ord('z')))
                          for i in range(128))
        if not all(c.islower() for c in key):
            raise ValueError('Key is not all lowercase: %s' % (key,))
        self.key = key

    def encode(self, plaintext):
        sanitized = sanitize(plaintext)
        if len(sanitized) > len(self.key):
            key = self.key * (len(sanitized)//len(self.key)+1)
        else:
            key = self.key
        return ''.join(shift(p[0], p[1])
                       for p in zip(sanitized, key))

    def decode(self, ciphertext):
        if len(ciphertext) > len(self.key):
            key = self.key * (len(ciphertext)//len(self.key)+1)
        else:
            key = self.key
        return ''.join(shift(p[0], p[1], reverse=True)
                       for p in zip(ciphertext, key))

class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d')
