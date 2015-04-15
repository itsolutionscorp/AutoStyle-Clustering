def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        return
    else:
        hamming_distance = 0

        for i in range(len(dna1)):
            if dna1[i] != dna2[i]:
                hamming_distance += 1

    return hamming_distance
