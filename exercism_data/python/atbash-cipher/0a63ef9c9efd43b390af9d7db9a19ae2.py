from string import ascii_lowercase
from re import sub

_ORD_MAP = {k:v for (k,v) in zip(ascii_lowercase, ascii_lowercase[::-1])}

def chunker(seq, size):
    return (seq[pos:pos + size] for pos in xrange(0, len(seq), size))

def encode(input_string):
    lowercase = input_string.lower()
    filtered = sub('[^0-9a-z]+', '', lowercase)
    return_list = list()
    for chunk in chunker(filtered, 5):
        reverse_chunk = ''
        for char in chunk:
            reverse_chunk += _ORD_MAP.get(char, char)
        return_list.append(reverse_chunk)
    return ' '.join(return_list)


def decode(input_string):
    return_value = ''
    for char in input_string:
        if char == ' ':
            continue
        return_value += _ORD_MAP.get(char, char)
    return return_value
