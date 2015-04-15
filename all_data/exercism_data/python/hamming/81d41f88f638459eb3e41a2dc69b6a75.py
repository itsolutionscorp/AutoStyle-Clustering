def distance(seq1, seq2):
    return sum(1 for i in range(len(seq1)) if seq1[i] != seq2[i])
