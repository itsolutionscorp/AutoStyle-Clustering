def distance(seq_a, seq_b):

    dist = 0

    for i in xrange(len(seq_a)):
        if seq_a[i] != seq_b[i]: dist += 1

    return dist
