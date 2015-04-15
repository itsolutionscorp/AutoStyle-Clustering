__author__ = 'citypulse-dp'


def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        raise Exception('DNA sequences must have the same length!')

    dist = 0
    for nucleoid1, nucleoid2 in zip(dna1, dna2):
        if nucleoid1 != nucleoid2:
            dist += 1
    return dist
