"""An implementation of the Square Code cipher."""

from __future__ import division
from math import sqrt, ceil
from string import maketrans, lowercase, uppercase, punctuation, whitespace
from itertools import product


def encode(data):
    """Encode the provided data using the square code cipher."""
    return EncodingSquare(data).encode()

def decode(data):
    """Decode the provided data using the square code cipher."""
    return DecodingSquare(data).decode()


class _SquareCode(object):

    TO_LOWER = maketrans(uppercase, lowercase)
    CHARS_TO_REMOVE = punctuation + whitespace

    def __init__(self, data):
        self.data = list(data.translate(self.TO_LOWER, self.CHARS_TO_REMOVE))
        self.data_length = len(self.data)
        self.size = int(ceil(sqrt(self.data_length)))
        self.capacity = self.size ** 2
        self.pad_length = self.capacity - self.data_length
        self._pad_square()

    def walk(self):
        return "".join(
            "".join(self.data[column :: self.size])
            for column in xrange(self.size)
        )

class EncodingSquare(_SquareCode):

    def _pad_square(self):
        self.data += [" "] * self.pad_length

    def encode(self):
        encoded = self.walk().replace(" ", "")
        chunks_of_5_characters = [
            encoded[offset : offset+5]
            for offset in xrange(0, len(encoded), 5)
        ]
        return " ".join(chunks_of_5_characters)

class DecodingSquare(_SquareCode):

    def _pad_square(self):
        pad_coordinates = [
            divmod(p, self.size)
            for p in xrange(0, self.pad_length)
        ]
        pad_offsets = sorted(
            self.capacity - bottom * self.size - right - 1
            for right, bottom in pad_coordinates
        )
        for pad_offset in pad_offsets:
            self.data.insert(pad_offset, " ")

    def decode(self):
        return self.walk().rstrip()
