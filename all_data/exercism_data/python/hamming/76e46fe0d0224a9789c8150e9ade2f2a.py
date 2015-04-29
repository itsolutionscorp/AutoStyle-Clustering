__author__ = 'lene'

def distance(dna1, dna2):
    if len(dna1) != len(dna2): raise ValueError
    paired_nucleotides = zip(dna1, dna2)
    mismatched_pairs = filter(lambda i: i[0] != i[1], paired_nucleotides)
    return len(mismatched_pairs)
