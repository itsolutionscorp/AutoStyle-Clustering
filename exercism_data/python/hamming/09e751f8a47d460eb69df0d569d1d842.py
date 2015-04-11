def hamming(dna1, dna2):
    matches = sum(1 if d1 != d2 else 0 for d1, d2 in zip(dna1, dna2))
    return matches + abs(len(dna1) - len(dna2))
