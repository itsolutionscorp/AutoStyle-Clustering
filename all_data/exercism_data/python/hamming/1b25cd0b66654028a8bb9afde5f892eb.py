def distance(strand1, strand2):
    distance = 0
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            distance += 1
    return distance
