"""An implementation of the Square Code cipher."""

from __future__ import division
from math import sqrt, ceil
import string

__all__ = ['encode', 'decode', 'SquareCodeEncoder', 'SquareCodeDecoder']


def encode(data):
    """Encode the provided data using the square code cipher."""
    return "".join(SquareCodeEncoder(data).stream())

def decode(data):
    """Decode the provided data using the square code cipher."""
    return "".join(SquareCodeDecoder(data).stream())

class SquareCode(object):

    VALID_CHARS = string.ascii_lowercase + string.digits

    def __init__(self, data):
        self.data = [c for c in data.lower() if c in self.VALID_CHARS]
        self.width = int(ceil(sqrt(len(self.data))))
        self.capacity = self.width ** 2
        self.fields = list(self.data) + [None]*(self.capacity-len(self.data))

    def _walk(self, skip_empty=True):
        for column in range(0, self.width):
            for row in range(0, self.width):
                offset = column + row * self.width
                value = self.fields[offset]
                if value is not None or not skip_empty:
                    yield value 

class SquareCodeEncoder(SquareCode):

    def stream(self):
        """Produces a character stream for the encoded data."""
        for idx, character in enumerate(self._walk()):
            if idx and not idx % 5: yield " ";
            yield character

class SquareCodeDecoder(SquareCode):

    def __init__(self, data):
        super(SquareCodeDecoder, self).__init__(data)
        self._mirror_layout()

    def _mirror_layout(self):
        data_values = (value for value in self.data)
        self.fields = [
            None if is_empty else data_values.next()
            for is_empty in [f is None for f in self._walk(skip_empty=False)]
        ]
        return self

    def stream(self):
        """Produces a character stream for the decoded data."""
        return self._walk()
