import re
import string

CHARS = string.ascii_lowercase
ANTICHARS = ''.join(reversed(CHARS))
CIPHER = str.maketrans(CHARS, ANTICHARS)


def _translate(phrase):
    return re.sub('(?![a-z]|[0-9]).', '', phrase.lower()).translate(CIPHER)


def encode(phrase):
    encoded = _translate(phrase)
    return ' '.join(encoded[i:i + 5] for i in range(0, len(encoded), 5))


def decode(phrase):
    return _translate(phrase)
