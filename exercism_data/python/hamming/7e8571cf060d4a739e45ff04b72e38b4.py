from itertools import izip


def distance(dna1, dna2):
    return sum(1 for nc1, nc2 in izip(dna1, dna2) if nc1 != nc2)
