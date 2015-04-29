def hamming(seq1, seq2):
    hamming = 0
    for base1, base2 in zip(seq1, seq2):
        if base1 != base2:
            hamming += 1
    if len(seq1) != len(seq2):
        hamming += abs(len(seq1) - len(seq2))
    return hamming
