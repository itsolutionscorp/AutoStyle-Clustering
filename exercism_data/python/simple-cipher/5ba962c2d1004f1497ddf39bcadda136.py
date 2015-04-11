import string
import random

class Caesar:

    def encode(self, msg):
        trans = string.maketrans('abcdefghijklmnopqrstuvwxyz', 'defghijklmnopqrstuvwxyzabc')
        return msg.lower().translate(trans, "1234567890'.,!? ")

    def decode(self, code):
        trans = string.maketrans('defghijklmnopqrstuvwxyzabc', 'abcdefghijklmnopqrstuvwxyz')
        return code.translate(trans)

class Cipher:

    def __init__(self, key=None):
        if key:
            self.key = key
        else:
            self.key = ''.join([chr(ord('a') + random.randint(0,25)) for i in range(100)])

    def encode(self, msg):
        key = self.key
        while len(key) < len(msg):
            key += key
        code = ''.join([chr((ord(m) + ord(k) - 2*ord('a'))%26 + ord('a')) for m, k in zip(msg, key)])
        return code

    def decode(self, code):
        key = self.key
        while len(key) < len(code):
            key += key
        msg = ''.join([chr((ord(c) - ord(k))%26 + ord('a')) for c, k in zip(code, key)])
        return msg
