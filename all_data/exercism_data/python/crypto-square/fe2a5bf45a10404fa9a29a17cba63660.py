"""An implementation of the Square Code cipher."""

from __future__ import division
from math import sqrt, ceil
import string
from itertools import product


def encode(data):
    """Encode the provided data using the square code cipher."""
    return "".join(EncodingSquare(data).stream())

def decode(data):
    """Decode the provided data using the square code cipher."""
    return "".join(DecodingSquare(data).stream())


class _SquareCode(object):

    VALID_CHARS = string.ascii_lowercase + string.digits

    def __init__(self, data):
        self.data = [c for c in data.lower() if c in self.VALID_CHARS]
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


from timeit import timeit
s = "x" * 50000
print "encode 1 x 50,000 chars", timeit(lambda: encode(s), number=1)

from timeit import timeit
s = "x" * 1
print "encode 50,000 x 1 char", timeit(lambda: encode(s), number=50000)

from timeit import timeit
s = "x" * 50000
print "decode 1 x 50,000 chars", timeit(lambda: decode(s), number=1)

from timeit import timeit
s = "x" * 1
print "decode 50,000 x 1 char", timeit(lambda: decode(s), number=50000)
