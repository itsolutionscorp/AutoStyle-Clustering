from string import maketrans, translate
import re

_TABLE = maketrans('abcdefghijklmnopqrstuvwxyz', 'abcdefghijklmnopqrstuvwxyz'[::-1])


def decode(s):
    cleaned = re.sub(r'\W', '', s.lower())
    return translate(cleaned, _TABLE)


def encode(s):
    e = decode(s)
    # Shamelessly lifted from http://stackoverflow.com/a/1751478/56333
    return " ".join([e[i:i + 5] for i in range(0, len(e), 5)])
