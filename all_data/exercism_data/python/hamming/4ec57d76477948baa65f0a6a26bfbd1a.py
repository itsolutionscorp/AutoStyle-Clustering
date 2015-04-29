def hamming(seq1, seq2):
    hamming_distance = abs(len(seq1) - len(seq2))
    for s1, s2 in zip(seq1, seq2):
        if s1 != s2:
            hamming_distance += 1
    return hamming_distance
