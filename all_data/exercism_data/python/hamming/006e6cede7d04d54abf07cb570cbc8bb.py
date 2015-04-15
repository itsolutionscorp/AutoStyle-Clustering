def distance(strand_a, strand_b):
    ''' return the Hamming Distance between two DNA strands '''

    if len(strand_a) != len(strand_b):
        raise Exception('strands must be of equal length')

    return len([x for x in zip(strand_a, strand_b) if x[0] != x[1]])
