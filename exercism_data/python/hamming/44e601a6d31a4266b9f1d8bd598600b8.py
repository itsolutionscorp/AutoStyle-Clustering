def distance(strand1, strand2):

    dist = 0

    if strand1 == strand2:
        return dist

    for i,v in enumerate(strand1):
        if strand1[i] != strand2[i]:
            dist += 1
        else:
            pass

    return dist
