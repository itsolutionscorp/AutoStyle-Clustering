def hamming(seq1,seq2):
    
    diff = len(seq1) - len(seq2)
    dist = len(seq2)
    if diff < 0:
        dist = len(seq1)
        diff = abs(diff)
    
    for i in range(0,dist):
        if seq1[i] != seq2[i]:
            diff += 1

    return diff
