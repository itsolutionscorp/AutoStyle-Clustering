from random import choice
import re

class Caesar(object):
    def encode(self, s):
        return Cipher("d").encode(s)

    def decode(self, s):
        return Cipher("d").decode(s)


class Cipher(object):
    valid_key_characters = tuple(chr(c) for c in range(ord("a"), ord("z") + 1))
    non_words = re.compile(r"[^a-z]")

    def __init__(self, key=None):
        self.key = "".join(choice(Cipher.valid_key_characters) for i in xrange(100)) if key is None else key
        if any(c not in Cipher.valid_key_characters for c in self.key):
            raise ValueError("Key contains invalid characters")
        self.key_shifts = tuple(ord(c) - ord("a") for c in self.key)

    def encode(self, s):
        clean = Cipher.non_words.sub("", s.lower())
        encoded = []
        key = self.get_key(len(s))
        for i, c in enumerate(clean):
            shifted = ord(c) + key[i]
            if shifted > ord("z"):
                shifted -= 26
            encoded.append(chr(shifted))
        return "".join(encoded)

    def decode(self, s):
        decoded = []
        key = self.get_key(len(s))
        for i, c in enumerate(s):
            shifted = ord(c) - key[i]
            if shifted < ord("a"):
                shifted += 26
            decoded.append(chr(shifted))
        return "".join(decoded)

    def get_key(self, length):
        key = self.key_shifts
        while len(key) < length:
            key += self.key_shifts
        return key
