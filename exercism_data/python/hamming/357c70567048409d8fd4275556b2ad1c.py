def hamming(seq1, seq2):
    small = min(len(seq1), len(seq2))
    big = max(len(seq1), len(seq2))
    hamming = big - small
    
    for index in range(small):
        if seq1[index] != seq2[index]:
            hamming += 1
    
    return hamming
