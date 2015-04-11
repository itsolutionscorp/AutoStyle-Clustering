def distance(strand1, strand2):
    dif = 0

    for i in range(0, len(strand1)):
        if strand1[i] != strand2[i]:
            dif += 1

    return dif
