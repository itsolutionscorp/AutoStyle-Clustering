from itertools import izip_longest
def hamming(strand1, strand2):
    return sum(1 for a, b in izip_longest(strand1, strand2) if a != b)
