__author__ = 'lene'

def distance(dna1, dna2):
    if len(dna1) != len(dna2): raise ValueError
    dist = 0
    for i, j in zip(dna1, dna2):
        if i != j: dist += 1
    return dist
