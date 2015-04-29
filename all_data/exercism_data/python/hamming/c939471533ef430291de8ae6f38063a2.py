def hamming(seq1, seq2):
    hd = abs(len(seq1) - len(seq2))
    for s1, s2 in zip(seq1, seq2):
        if s1 != s2:
            hd+=1
    return hd
