#!/usr/bin/env python
from re import sub

def encode(text):
    return _chunk([_cipher(i) for i in _clean(text)])

def decode(text):
    return "".join([_cipher(i) for i in _clean(text)])

# non-public methods
def _clean(text):
    """Takes some text and returns a lowercase/alphnumeric spaceless string.
    """
    return sub(r'[\W_]+', '', text.lower().replace(" ",""))

def _cipher(character):
    """Returns single string character based on cipher rules. This could
    probably be written much more efficiently or via some built-in, I just
    haven't discovered it yet.
    """
    alpha = "abcdefghijklmnopqrstuvwxyz0123456789"
    cipher = "zyxwvutsrqponmlkjihgfedcba0123456789"
    return "".join([c for p, c in zip(alpha, cipher) if character == p])

def _chunk(ciphered_list):
    """Takes a list and returns a chunked string. I'm sure there is a more
    clever method to handle this.
    """
    if len(ciphered_list) < 5:
        return "".join(ciphered_list)
    else:
        index = 0
        chunked_string = ""
        while index < len(ciphered_list):
            if (index + 1) % 5 == 0 and (index + 1) != len(ciphered_list):
                chunked_string = chunked_string + ciphered_list[index] + " "
                index += 1
            else:
                chunked_string = chunked_string + ciphered_list[index]
                index += 1
        return chunked_string
