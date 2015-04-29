def distance(seq1, seq2):
    tot = 0
    for pos in range(len(seq1)):
        if seq1[pos] != seq2[pos]:
            tot += 1
    return tot
