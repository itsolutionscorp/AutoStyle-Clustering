def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        raise ValueError
    return sum([n1 != n2 for (n1, n2) in zip(dna1, dna2)])
