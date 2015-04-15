import re
import string

from functools import reduce
from itertools import zip_longest
from operator import add
from unicodedata import category


atbash = lambda alphabet: str.maketrans(dict(zip(alphabet, reversed(alphabet))))


def _split_every(n: int, seq: iter, fillvalue=None):
    for tup in zip_longest(*[seq] * n, fillvalue=fillvalue):
        yield reduce(add, tup)


def decode(s: str, alphabet=string.ascii_lowercase) -> str:
    cipher = atbash(alphabet)
    cipher_text = s.replace(" ", "")
    return cipher_text.translate(cipher)


def encode(s: str, alphabet=string.ascii_lowercase, word_size=5) -> str:
    cipher = atbash(alphabet)
    cipher_text = (s.lower()
                    .replace(" ", "")
                    .translate(cipher))
    cipher_text = "".join(ch for ch in cipher_text if category(ch)[0] != "P")
    return " ".join(_split_every(word_size, iter(cipher_text), ""))
