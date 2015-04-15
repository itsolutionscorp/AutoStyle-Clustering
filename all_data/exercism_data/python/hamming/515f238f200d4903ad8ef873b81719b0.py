def hamming(seq1, seq2):
    minlength = min(len(seq1),len(seq2))
    hdistance = 0
    for nucleotides in range(minlength):
        if seq1[nucleotides] != seq2[nucleotides]:
            hdistance += 1
    hdistance += abs(len(seq1) - len(seq2))
    return hdistance
