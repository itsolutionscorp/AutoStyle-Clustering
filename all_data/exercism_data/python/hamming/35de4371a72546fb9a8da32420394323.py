from itertools import izip_longest

def hamming(dna1, dna2):
    return sum(a != b for a, b in izip_longest(dna1, dna2))
