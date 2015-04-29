def hamming(strand1, strand2):
    dist = 0
    strand1_len = len(strand1)
    strand2_len = len(strand2)

    for i in range(min(strand1_len, strand2_len)):
        if strand1[i] != strand2[i]:
            dist += 1
    dist += abs(strand1_len - strand2_len)
    return dist
