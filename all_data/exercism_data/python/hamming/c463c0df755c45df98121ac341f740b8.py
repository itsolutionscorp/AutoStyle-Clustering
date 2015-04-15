def distance(dna1, dna2):
    return sum([1 for nuc1, nuc2 in zip(dna1, dna2) if nuc1 != nuc2])
