def distance(strand1, strand2):
    distance = 0
    for n1, n2 in zip(strand1, strand2):
        distance += int(n1 != n2)
    return distance
