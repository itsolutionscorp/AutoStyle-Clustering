import string
import random
import re


class Cipher:
    def __init__(self, key=None):
        if not key:
            self.key = ''.join([random.choice(string.ascii_lowercase) for i in range(0, 100)])
        else:
            self.key = key

    def encode(self, text):
        t = re.sub('[ 0-9!.,\']', '', text)
        t = t.lower()
        encoded = ''
        for i in range(0, len(t)):
            offset = ord(self.key[i%len(self.key)]) - ord('a')
            encoded += chr(ord(t[i]) + offset) if (ord(t[i]) + offset) <= ord('z') else chr(ord(t[i]) + offset - 26)

        return encoded

    def decode(self, text):
        t = re.sub('[ 0-9!.,\']', '', text)
        t = t.lower()
        decoded = ''
        for i in range(0, len(t)):
            offset = ord(self.key[i%len(self.key)]) - ord('a')
            decoded += chr(ord(t[i]) - offset) if (ord(t[i]) - offset) >= ord('a') else chr(ord(t[i]) - offset + 26)

        return decoded


class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, 'd' * 100)
