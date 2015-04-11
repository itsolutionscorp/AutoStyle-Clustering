from collections import OrderedDict
import string

CIPHER_KEY = string.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1])

def encode(text):

    text = text.translate(None, '!.;, ').lower()

    return _chunk(text.translate(CIPHER_KEY))
    
def decode(text):

    text = text.translate(None, '!.;, ').lower()
    
    return text.translate(CIPHER_KEY)

def _chunk(text):
    
    if len(text) <= 5:
        return text
    return text[:5] + ' ' + _chunk(text[5:])
