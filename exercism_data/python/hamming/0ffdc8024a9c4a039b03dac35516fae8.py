def distance(seq1, seq2):
    """ Hamming Distance
    Inputs: 2 DNA sequences
    Outputs: hamming distance between the two sequences(no alignment performed)
    """
    distance = 0
    length = len(seq1)
    i = 0
    while i < length:
        if seq1[i] != seq2[i]:
            distance += 1
        i += 1
    return distance
