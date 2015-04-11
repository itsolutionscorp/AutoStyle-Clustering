def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        raise ValueError("Undefined for sequences of unequal length")
    return sum(ch1 != ch2 for ch1, ch2 in zip(dna1, dna2))
