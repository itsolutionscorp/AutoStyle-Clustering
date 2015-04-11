"""The Atbash cipher."""

import string


SUBSTITUTION_ALPHABET = \
    string.maketrans(string.lowercase, "".join(reversed(string.lowercase)))

_PUNCSPACE = string.punctuation + string.whitespace


def encode(plain, block_size=5):
    """Encode by the Atbash cipher."""
    encoded = plain.lower().translate(SUBSTITUTION_ALPHABET, _PUNCSPACE)
    return " ".join(
        encoded[i:(i + block_size)]
        for i in xrange(0, len(encoded), block_size)
    )


def decode(encoded):
    """Decode by the Atbash cipher."""
    assert all(char.islower() or char.isspace() for char in encoded)
    return encoded.translate(SUBSTITUTION_ALPHABET, string.whitespace)
