def distance(dna1, dna2):
    hamming = 0
    for base1, base2 in zip(dna1, dna2):
        if base1 != base2:
            hamming += 1
    return hamming
