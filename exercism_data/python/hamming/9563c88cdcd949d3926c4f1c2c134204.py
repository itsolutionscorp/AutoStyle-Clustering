def distance(strand1, strand2):
    distance = 0

    for base1, base2 in zip(strand1, strand2):
        if base1 != base2:
            distance += 1

    return distance
