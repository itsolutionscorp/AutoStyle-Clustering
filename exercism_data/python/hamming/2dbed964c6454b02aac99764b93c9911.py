#hamming.py


def distance(seq1, seq2):
    if len(seq1) != len(seq2):
        raise "Error: sequences are not of the same length"
    hamming_distance = 0
    for x in range(0, len(seq1)):
        if seq1[x] != seq2[x]:
            hamming_distance += 1
    return hamming_distance
