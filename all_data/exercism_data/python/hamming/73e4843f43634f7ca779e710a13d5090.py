def distance(strand1, strand2):
    hamming_distance = 0
    strand_len = len(strand1)

    for i in range(0, strand_len):
        if strand1[i] != strand2[i]:
            hamming_distance = hamming_distance + 1

    return hamming_distance
