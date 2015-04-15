from itertools import zip_longest
from math import sqrt, ceil
from textwrap import wrap

def normalize(message: str) -> str:
    return ''.join(c for c in message.lower() if c.isalnum())

def rows(message: str) -> 'List[str]':
    if not message:
        return ['']
    return wrap(message, ceil(sqrt(len(message))))

def transpose(rows: 'List[str]') -> 'List[str]':
    return [''.join(x) for x in zip_longest(*rows, fillvalue='')]

def encode(message: str) -> str:
    return ' '.join(transpose(rows(normalize(message))))
