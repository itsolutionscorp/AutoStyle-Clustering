"""Substitution ciphers."""

import random
import string


class Cipher(object):
    """A substitution cipher."""

    def __init__(self, key=None):
        if key is None:
            key = (random.choice(string.ascii_lowercase) for _ in xrange(100))
        self.key = [ord(k) - ORD_a for k in key]
        self.key_len = len(self.key)

    def encode(self, plain):
        """Encode by the substitution cipher."""
        plain = filter(lambda char: char in string.ascii_letters, plain)
        plain = plain.lower()
        return "".join(
            chr((ord(letter) - ORD_a + self.key[i % self.key_len]) % ASCII_LOWERCASE_LEN + ORD_a)
            for i, letter in enumerate(plain)
        )

    def decode(self, cipher):
        """Decode by the substitution cipher."""
        assert all(char.islower() for char in cipher)
        return "".join(
            chr((ord(letter) - ORD_a + ASCII_LOWERCASE_LEN - self.key[i % self.key_len]) % ASCII_LOWERCASE_LEN + ORD_a)
            for i, letter in enumerate(cipher)
        )


class Caesar(Cipher):
    """A Caesar cipher."""

    def __init__(self):
        super(Caesar, self).__init__("d")


ASCII_LOWERCASE_LEN = len(string.ascii_lowercase)

ORD_a = ord("a")
