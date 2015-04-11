import string 

_ring = string.maketrans(string.ascii_lowercase, ''.join(reversed(string.ascii_lowercase)))
_delete_chars = string.punctuation + string.whitespace

def decode(text):
    return text.lower().translate(_ring, _delete_chars)

def encode(text):
    text = decode(text)
    return ' '.join(text[i:i+5] for i in xrange(0, len(text), 5))
