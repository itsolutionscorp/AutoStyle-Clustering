import itertools

def hamming(strand1, strand2):
    return sum(x != y for x, y in itertools.izip_longest(strand1, strand2))
