from itertools import zip_longest
import re
from string import ascii_lowercase

CIPHER = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])

def chunk(iterable, n):
    def grouper(iterable, n):
        """grouper recipe"""
        return zip_longest(*[iter(iterable)]*n, fillvalue='')
    return " ".join(''.join(chunk) for chunk in grouper(iterable, n))    

def encode(text):
    global CIPHER
    block_text = ''.join(re.findall("\w", text)).lower().translate(CIPHER)
    return chunk(block_text,5)

def decode(text):
    global CIPHER
    return ''.join(re.findall("\w", text)).lower().translate(CIPHER)
