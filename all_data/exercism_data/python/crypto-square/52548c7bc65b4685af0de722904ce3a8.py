from math import sqrt, ceil


def encode(line):
    line = ''.join(filter(str.isalnum, line)).lower()
    sz = ceil(sqrt(len(line)))
    return ' '.join(line[i::sz] for i in range(sz))
