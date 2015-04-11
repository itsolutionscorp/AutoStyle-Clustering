from itertools import izip_longest

def hamming(p, q):
    return sum([b1 != b2 for b1, b2 in izip_longest(p, q)])
