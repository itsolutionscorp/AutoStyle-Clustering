def distance(strand1, strand2):
    return sum(dna1 != dna2 for dna1, dna2 in zip(strand1, strand2))
