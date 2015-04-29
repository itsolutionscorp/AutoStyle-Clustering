def hamming(dna1, dna2):
    matches = [d1 == d2 for d1, d2 in zip(dna1, dna2)]
    return matches.count(False) + abs(len(dna1) - len(dna2))
