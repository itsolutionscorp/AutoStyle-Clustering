def distance(seq1, seq2):
    '''Compare two DNA sequences and calculate the
    Hamming distance (count of base mutations).'''
    return sum(base_differences(seq1, seq2))


def base_differences(seq1, seq2):
    for i in xrange(len(seq1)):
        if seq1[i] != seq2[i]:
            yield 1
