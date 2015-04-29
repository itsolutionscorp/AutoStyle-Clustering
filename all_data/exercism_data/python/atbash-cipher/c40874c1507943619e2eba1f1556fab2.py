import re

_ORD_A = ord('a')
_ORD_Z = ord('z')
_ORD_SUM = _ORD_A + _ORD_Z

_ORD_MAP = dict()

def _build_map():
    for key in range(_ORD_A, _ORD_Z + 1):
        character = chr(key)
        _ORD_MAP[character] = chr(_ORD_SUM - key)

_build_map()

def chunker(seq, size):
    return (seq[pos:pos + size] for pos in xrange(0, len(seq), size))

def encode(input_string):
    lowercase = input_string.lower()
    filtered = re.sub('[^0-9a-z]+', '', lowercase)
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
