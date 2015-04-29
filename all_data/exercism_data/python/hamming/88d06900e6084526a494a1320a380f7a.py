'''This module computes the Hamming distance'''

def distance(dna1, dna2):
    '''Computes Hamming distance'''

    hamming_dist = 0

    if len(dna2) < len(dna1):
        dna1, dna2 = dna2, dna1

    for indx, base in enumerate(dna1):
        if base != dna2[indx]:
            hamming_dist = hamming_dist + 1

    return hamming_dist
