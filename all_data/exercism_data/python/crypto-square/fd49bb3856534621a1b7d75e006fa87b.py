from __future__ import division
from math import sqrt, ceil
import string


def encode(text):
    """Encode the provided text using the square code cipher."""
    return "".join(_EncodingSquare(text).stream())

def decode(encoded):
    """Decode the provided data using the square code cipher."""
    return "".join(_DecodingSquare(encoded).stream())

class _Square(object):

    VALID_CHARS = string.ascii_lowercase + string.digits

    def __init__(self, data):
        self.data = [c for c in data.lower() if c in self.VALID_CHARS]
        self.width = int(ceil(sqrt(len(self.data))))
        self.capacity = self.width ** 2
        self.fields = list(self.data) + [None]*(self.capacity-len(self.data))

    def walk(self, skip_None=True):
        for column in range(0, self.width):
            for row in range(0, self.width):
                offset = column + row * self.width
                field_data = self.fields[offset]
                if field_data is not None or not skip_None:
                    yield field_data

class _EncodingSquare(_Square):

    def stream(self):
        for idx, character in enumerate(self.walk()):
            if idx and not idx % 5: yield " ";
            yield character

class _DecodingSquare(_Square):

    def __init__(self, data):
        super(_DecodingSquare, self).__init__(data)
        self.mirror_layout()

    def mirror_layout(self):
        mirrored_fields = [None] * self.capacity
        data_offset = 0
        for offset, field_data in enumerate(self.walk(False)):
            if field_data is not None:
                mirrored_fields[offset] = self.data[data_offset]
                data_offset += 1
        self.fields = mirrored_fields
        return self

    def stream(self):
        return self.walk()
