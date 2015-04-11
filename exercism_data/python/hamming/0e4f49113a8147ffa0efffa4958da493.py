def distance(sequence1, sequence2):
    hamming = 0
    for i in range(len(sequence1)):
        if sequence1[i] != sequence2[i]:
            hamming += 1
    return hamming
