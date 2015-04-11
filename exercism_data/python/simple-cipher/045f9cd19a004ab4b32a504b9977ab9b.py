# Python 3

"""Substitution ciphers."""

from itertools import cycle
import random
from string import ascii_letters, ascii_lowercase


class ArgumentError(ValueError):
    pass


class Cipher(object):
    """A substitution cipher."""

    def __init__(self, key=None):
        if key is None:
            key = [random.choice(ascii_lowercase) for _ in range(100)]
        self.key = key

    @property
    def key(self):
        return "".join(chr_from_a(k) for k in self._key)

    @key.setter
    def key(self, value):
        if any(v not in ascii_lowercase for v in value):
            raise ArgumentError(value)
        self._key = [ord_from_a(v) for v in value]

    def encode(self, plain):
        """Encode by the substitution cipher."""
        plain = "".join(char for char in plain if char in ascii_letters)
        plain = plain.lower()
        return "".join(
            shift(letter, k)
            for letter, k in zip(plain, cycle(self._key))
        )

    def decode(self, cipher):
        """Decode by the substitution cipher."""
        assert all(char in ascii_lowercase for char in cipher)
        return "".join(
            shift(letter, -k)
            for letter, k in zip(cipher, cycle(self._key))
        )


class Caesar(Cipher):
    """A Caesar cipher."""

    def __init__(self):
        super().__init__("d")


def shift(letter, n):
    """Return a letter shifted from ``letter`` by ``n`` in the ASCII lowercase letters."""
    assert letter in ascii_lowercase
    assert -ASCII_LOWERCASE_LEN <= n <= ASCII_LOWERCASE_LEN
    if n < 0:
        n += ASCII_LOWERCASE_LEN
    return chr_from_a(ord_from_a(letter) + n)


def ord_from_a(char):
    """Return the ordinal of a character from 'a'."""
    return ord(char) - ord("a")


def chr_from_a(nth):
    """Return the nth character from 'a' in the ASCII lowercase letters."""
    assert nth >= 0
    nth %= ASCII_LOWERCASE_LEN
    return chr(ord("a") + nth)


ASCII_LOWERCASE_LEN = len(ascii_lowercase)
