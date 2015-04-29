__author__ = 'djdick'


def distance(dna1,dna2):
    hamming_distance = 0

    for i in range(len(dna1)):
        if not dna1[i] == dna2[i]:
            hamming_distance += 1

    return hamming_distance
