def distance(seq1, seq2):
    return sum(1 for a, b in zip(seq1, seq2) if a != b)
