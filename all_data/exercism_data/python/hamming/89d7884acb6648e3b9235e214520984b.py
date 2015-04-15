def distance(seq1, seq2):
    count = 0
    length = len(seq1)
    hamming = 0
    while count < length:
        if seq1[count] != seq2[count]:
            hamming += 1
            count += 1
        else:
            count += 1
    return hamming
