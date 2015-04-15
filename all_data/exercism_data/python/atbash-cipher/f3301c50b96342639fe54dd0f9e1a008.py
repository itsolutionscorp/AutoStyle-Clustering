from string import maketrans, ascii_lowercase, punctuation, whitespace
from textwrap import wrap


table = maketrans(ascii_lowercase, ascii_lowercase[::-1])


def decode(text):
    return text.translate(table, whitespace)


def encode(text):
    return ' '.join(wrap(text.lower().translate(table, punctuation+whitespace), 5))
