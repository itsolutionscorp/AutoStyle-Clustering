"""An implementation of the Square Code cipher."""

from __future__ import division
from math import sqrt, ceil
from string import maketrans, ascii_lowercase, \
                   ascii_uppercase, punctuation, whitespace
from itertools import product


def encode(data):
    """Encode the provided data using the square code cipher."""
    return "".join(EncodingSquare(data).stream())

def decode(data):
    """Decode the provided data using the square code cipher."""
    return "".join(DecodingSquare(data).stream())


class _SquareCode(object):

    TO_LOWER = maketrans(ascii_uppercase, ascii_lowercase)
    CHARS_TO_REMOVE = punctuation + whitespace

    def __init__(self, data):
        self.data = list(data.translate(self.TO_LOWER, self.CHARS_TO_REMOVE))
        self.data_length = len(self.data)
        self.size = int(ceil(sqrt(self.data_length)))
        self.capacity = self.size ** 2
        self.pad_length = self.capacity - self.data_length
        self._pad_square()

    def walk(self):
        return (
            value 
            for column in xrange(0, self.size)
            for value in self.data[column :: self.size]
            if value
        )

class EncodingSquare(_SquareCode):

    def _pad_square(self):
        self.data += [None] * self.pad_length

    def stream(self):
        """Produces a character stream for the encoded data."""
        for idx, character in enumerate(self.walk()):
            if idx and not idx % 5: yield " ";
            yield character

class DecodingSquare(_SquareCode):

    def _pad_square(self):
        pad_coordinates = (
            divmod(p, self.size)
            for p in xrange(0, self.pad_length)
        )
        pad_offsets = sorted(
            self.capacity - bottom * self.size - right - 1
            for right, bottom in pad_coordinates
        )
        for pad_offset in pad_offsets:
            self.data.insert(pad_offset, None)

    def stream(self):
        """Produces a character stream for the decoded data."""
        return self.walk()
