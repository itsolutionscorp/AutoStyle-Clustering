def distance(seq_a, seq_b):
    hamming_dist = 0
    if len(seq_a) != len(seq_b):
        return -1

    for i in xrange(len(seq_a)):
        if seq_a[i] != seq_b[i]:
            hamming_dist += 1

    return hamming_dist
