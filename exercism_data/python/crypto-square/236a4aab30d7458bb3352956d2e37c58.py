from math import sqrt, ceil
from string import maketrans, uppercase, lowercase, punctuation, whitespace

__all__ = ["encode", "decode"]

_TO_LOWER = maketrans(uppercase, lowercase)
_ILLEGAL_CHARS = punctuation + whitespace

def encode(message):
    normalized = _normalize(message)
    encoded = "".join(_walk_columns(normalized))
    spaced = _separate_words(encoded, 5)
    return spaced

def decode(message):
    normalized = _normalize(message)
    padded = _pad_message(normalized)
    decoded = "".join(_walk_columns(padded))
    stripped = decoded.strip()
    return stripped

def _normalize(message):
    return message.translate(_TO_LOWER, _ILLEGAL_CHARS)

def _pad_message(message):
        square_size, square_width = _square_dimensions(message)
        message = list(message)
        pad_coordinates = (
            divmod(p, square_width)
            for p in xrange(0, square_size - len(message))
        )
        pad_offsets = sorted(
            square_size - y * square_width - x - 1
            for x, y in pad_coordinates
        )
        for pad_offset in pad_offsets:
            message.insert(pad_offset, " ")
        return "".join(message)

def _columns(message):
    square_size, square_width = _square_dimensions(message)
    square = list(message)
    return [
        square[i: square_size: square_width]
        for i in xrange(square_width)
    ]

def _walk_columns(message):
    for column in _columns(message):
        for char in column:
            yield char

def _square_dimensions(message):
    square_width = int(ceil(sqrt(len(message))))
    square_size = square_width ** 2
    return (square_size, square_width)

def _separate_words(message, wordsize):
    words = [
        message[i: i+wordsize]
        for i in xrange(0, len(message), wordsize)
    ]
    return " ".join(words)
