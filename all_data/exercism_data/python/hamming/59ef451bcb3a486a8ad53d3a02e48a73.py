from itertools import izip_longest

def hamming(dna1, dna2):
    return sum(1 for x, y in izip_longest(dna1, dna2) if x != y)
