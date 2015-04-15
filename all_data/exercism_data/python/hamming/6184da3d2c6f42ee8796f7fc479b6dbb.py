def distance(seq1, seq2):
    ct = 0
    for x in range(len(seq1)):
        if seq1[x] != seq2[x]:
            ct+=1
    return ct
