def distance(seqA, seqB):
    hamming_distance = 0

    for index, value in enumerate(seqB):
        if seqA[index] != seqB[index]:
            hamming_distance += 1

    return hamming_distance
