# exercism problem 5 compute hamming distance


def distance(dna_1, dna_2):
    hamming_distance = 0
    for i in range(len(dna_1)):
        if dna_1[i]<>dna_2[i]:
            hamming_distance += 1
    return hamming_distance
