def distance(strand1, strand2):
    distance = 0
    for index, _ in enumerate(strand1):
        if strand1[index] != strand2[index]:
            distance += 1
    return distance
