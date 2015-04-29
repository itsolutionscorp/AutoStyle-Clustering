def distance(strand1, strand2):
    if len(strand1) != len(strand2):
        return -1
    hdist = 0
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            hdist += 1
    return hdist
