def distance(seq1, seq2):
    """
    Calculate the Hamming distance between `seq1` and `seq2`.
    """
    return sum(1 for c1, c2 in zip(seq1, seq2) if c1 != c2)
