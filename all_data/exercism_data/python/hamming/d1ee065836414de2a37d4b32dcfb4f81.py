def distance(dna1, dna2):
    hamming_count = 0
    for c in range(len(dna1)):
        if dna1[c] != dna2[c]:
            hamming_count += 1
    return hamming_count
