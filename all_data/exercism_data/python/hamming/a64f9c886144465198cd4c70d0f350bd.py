def hamming(strand1, strand2):
    hamming_distance = 0
    for i in range(0, min(len(strand1), len(strand2))):
        if strand1[i] != strand2[i]:
            hamming_distance += 1
    hamming_distance += max(
        len(strand1),
        len(strand2)) - min(len(strand1),
                            len(strand2))
    return hamming_distance
