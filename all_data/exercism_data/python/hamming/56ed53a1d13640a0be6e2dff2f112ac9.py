def distance(rna1, rna2):
    hamming_distance = 0
    for nuc in range(len(rna1)):
        if rna1[nuc] != rna2[nuc]:
            hamming_distance += 1
    return hamming_distance
