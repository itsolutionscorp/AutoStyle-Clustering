def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        return -1

    if dna1 == dna2:
        return 0

    hamming_distance = 0
    for i in range(0, len(dna1)):
        if dna1[i] != dna2[i]:
            hamming_distance = hamming_distance + 1

    return hamming_distance
