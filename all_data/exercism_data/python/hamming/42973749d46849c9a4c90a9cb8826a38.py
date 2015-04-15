def distance(strand1, strand2):
    hamming_dist = 0
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]: hamming_dist += 1
    return hamming_dist
