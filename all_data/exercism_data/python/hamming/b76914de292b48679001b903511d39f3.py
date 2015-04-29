from itertools import zip_longest

def hamming(dna1, dna2):
    counter = 0
    for nucleotide1, nucleotide2 in zip_longest(dna1, dna2):
        if nucleotide1 != nucleotide2:
            counter += 1
    return counter
