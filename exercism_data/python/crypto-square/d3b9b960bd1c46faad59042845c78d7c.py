from math import sqrt, ceil
from string import punctuation, whitespace
from itertools import islice, ifilter
from re import findall

__all__ = ["encode", "decode"]

def normalize(message):
    return message.lower().translate(None, punctuation + whitespace)

def separate_words(message):
    return " ".join(findall(".{1,5}", message))

def icolumns(message):
    square_width = int(ceil(sqrt(len(message))))
    square_size = square_width ** 2
    square = list(message) + [None] * (square_size - len(message))
    for column in xrange(square_width):
        yield islice(square, column, square_size, square_width)

def walk_columns(message):
    for column in icolumns(message):
        for char in column:
            yield char

def istuff_message(message):
    original_chars = iter(message)
    for exists in walk_columns(message):
        if not exists:
            yield " "
        else:
            yield next(original_chars)

def stuff_message(message):
    return "".join(istuff_message(message))

def encode(message):
    normalized = normalize(message)
    encoded = "".join(ifilter(None, walk_columns(normalized)))
    spaced = separate_words(encoded)
    return spaced

def decode(message):
    normalized = normalize(message)
    stuffed = stuff_message(normalized)
    decoded = "".join(walk_columns(stuffed))
    stripped = decoded.translate(None, whitespace)
    return stripped
