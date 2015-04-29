def distance(seq1,seq2):
    counter = 0
    for i in range(len(seq1)):
        if seq1[i] != seq2[i]:
            counter += 1
    return counter
