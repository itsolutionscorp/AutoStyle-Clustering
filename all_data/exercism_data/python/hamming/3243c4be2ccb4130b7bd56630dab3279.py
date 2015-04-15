def distance(strand1, strand2):
    '''
    Returns the Hamming distance for
    the two given strands
    '''

    # both strands must be the same length
    assert len(strand1) == len(strand2)
    h_dist = 0
    # compare nucleotides at same position in both strands
    for x, y in zip(strand1, strand2):
        if x != y:
            h_dist += 1
    return h_dist
