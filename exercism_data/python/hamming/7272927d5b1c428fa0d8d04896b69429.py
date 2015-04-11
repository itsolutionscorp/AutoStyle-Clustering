def distance(strand_1, strand_2):
    min_len = min(len(strand_1),len(strand_2))
    dist = 0
    for n in range(0, min_len):
        if (strand_1[n] != strand_2[n]):
            dist += 1
    return dist
