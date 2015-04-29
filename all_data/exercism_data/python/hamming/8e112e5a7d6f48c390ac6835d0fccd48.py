def hamming(strand1, strand2):
    strandlen = min(len(strand1), len(strand2))
    dist = abs(len(strand1) -len(strand2))

    for i in range(0, strandlen):
        if strand1[i] != strand2[i]:
            dist += 1;

    return dist;
