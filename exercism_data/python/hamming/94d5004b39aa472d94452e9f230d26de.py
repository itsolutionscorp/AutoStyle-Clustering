def distance(dna1, dna2):
    hamming_distance = 0
    for i in range(0, len(dna1)):
        if dna1[i] != dna2[i]:
            hamming_distance += 1
    return hamming_distance
