__author__ = 'lene'

def distance(dna1, dna2):
    if len(dna1) != len(dna2): raise ValueError
    paired_nucleotides = zip(dna1, dna2)
    mismatched_pairs = filter(lambda pair: pair[0] != pair[1], paired_nucleotides)
    return len(mismatched_pairs)
