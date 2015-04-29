from itertools import izip

def hamming(dna1, dna2):
    difference = abs(len(dna1) - len(dna2))
    distance = sum(a != b for a, b in izip(dna1, dna2))
    return difference + distance
