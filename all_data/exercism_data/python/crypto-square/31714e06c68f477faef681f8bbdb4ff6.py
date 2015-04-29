"""An implementation of the Square Code cipher."""

from __future__ import division
from math import sqrt, ceil
from itertools import product
from string import maketrans, lowercase, uppercase, punctuation, whitespace

TO_LOWER = maketrans(uppercase, lowercase)
CHARS_TO_REMOVE = punctuation + whitespace


def encode(data):
    """Encode the provided data using the square code cipher."""
    square = _create_square(data, _pad_square_for_encoding)
    encoded = _walk_square(square)
    chunks_of_5_characters = [
        encoded[offset : offset+5]
        for offset in xrange(0, len(encoded), 5)
    ]
    return " ".join(chunks_of_5_characters)

def _pad_square_for_encoding(square):
    data, size = square
    pad_length = size ** 2 - len(data)
    data.extend([None] * pad_length)

def decode(data):
    """Decode the provided data using the square code cipher."""
    square = _create_square(data, _pad_square_for_decoding)
    return _walk_square(square)

def _pad_square_for_decoding(square):
    data, size = square
    pad_length = size ** 2 - len(data)
    padding_coordinates = (
        divmod(p, size)
        for p in xrange(0, pad_length)
    )
    padding_offsets = sorted(
        size ** 2 - bottom * size - right - 1
        for right, bottom in padding_coordinates
    )
    for padding_offset in padding_offsets:
        data.insert(padding_offset, None)

def _create_square(data, pad_square):
    data = list(data.translate(TO_LOWER, CHARS_TO_REMOVE))
    size = int(ceil(sqrt(len(data))))
    square = (data,size)
    pad_square(square)
    return square

def _walk_square(square):
    data, size = square
    return "".join(
        value 
        for column in xrange(0, size)
        for value in data[column :: size]
        if value
    )

from timeit import timeit
s = "x" * 50000
print "encode 1 x 50,000 chars", timeit(lambda: encode(s), number=1)
s = "x" * 1
print "encode 50,000 x 1 char", timeit(lambda: encode(s), number=50000)
s = "x" * 50000
print "decode 1 x 50,000 chars", timeit(lambda: decode(s), number=1)
s = "x" * 1
print "decode 50,000 x 1 char", timeit(lambda: decode(s), number=50000)
