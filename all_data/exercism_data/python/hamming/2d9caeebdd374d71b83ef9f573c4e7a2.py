def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        raise ValueError('Hamming distance is only defined for sequences of equal length')

    # count number of different elements
    return sum((a != b) for a, b in zip(dna1, dna2))
