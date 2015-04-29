import math
import string
import itertools


def join(iterator):
    return ''.join(iterator)


def normalize(text):
    remove = string.punctuation + string.whitespace
    return join(ch for ch in text.lower() if ch not in remove)


def split_into_chunks(l, n):
    n = max(1, n)
    return [l[i:i + n] for i in range(0, len(l), n)]


def encode(plaintext):
    plaintext = normalize(plaintext)

    cols = math.ceil(math.sqrt(len(plaintext)))

    rows = split_into_chunks(plaintext, cols)

    chunks = [join(chunk) for chunk in itertools.zip_longest(*rows, fillvalue='')]

    return ' '.join(chunks)
