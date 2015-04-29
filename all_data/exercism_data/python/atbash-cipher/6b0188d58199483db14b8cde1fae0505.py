from itertools import izip_longest
from string import (
    lowercase,
    maketrans,
    digits,
    punctuation,
    whitespace,
    translate
)


def encode(text):
    text = text.lower()
    table = maketrans(lowercase + digits, lowercase[::-1] + digits)
    trans = translate(text, table, deletions=punctuation + whitespace)
    return ' '.join(''.join(s) for s in izip_longest(*[iter(trans)] * 5,
                                                     fillvalue=''))


def decode(text):
    table = maketrans(lowercase[::-1] + digits, lowercase + digits)
    return translate(text, table, deletions=' ')
