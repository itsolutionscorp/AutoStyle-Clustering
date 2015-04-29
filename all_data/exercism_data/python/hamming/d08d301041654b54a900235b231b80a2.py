from itertools import izip

def hamming(dna1, dna2):
    distance = abs(len(dna1) - len(dna2))
    for a, b in izip(dna1, dna2):
        if a != b: distance += 1
    return distance
