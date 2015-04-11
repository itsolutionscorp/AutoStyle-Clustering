def distance(strand1, strand2):
    d = 0
    for i in range(len(strand1)):
        d = d+1 if strand1[i] != strand2[i] else d
    return d
