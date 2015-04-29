def distance(seq1, seq2):
    if len(seq1) != len(seq2):
        raise ValueError('DNA strings should be the same length')
    return sum(i != j for i, j in zip(seq1, seq2))
