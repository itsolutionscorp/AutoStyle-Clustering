__author__ = 'romleinster'


def distance(sequence1, sequence2):
    hamming_distance = 0
    for i in range(0,len(sequence1)):
        if sequence1[i] != sequence2[i]:
            hamming_distance += 1
    return hamming_distance
