def hamming(a_seq, b_seq):
    dist = 0
    for a, b in zip(a_seq, b_seq):
        if a != b:
            dist += 1
    if len(a_seq) != len(b_seq):
        dist += abs(len(a_seq) - len(b_seq))
    return dist
