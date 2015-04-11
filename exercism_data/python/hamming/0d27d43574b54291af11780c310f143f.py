def distance(seq_a, seq_b):
    '''
    computes the hamming distance between two sequences
    '''

    dist = 0

    for nuc_a, nuc_b in zip(seq_a, seq_b):
        if nuc_a != nuc_b: dist += 1

    return dist
