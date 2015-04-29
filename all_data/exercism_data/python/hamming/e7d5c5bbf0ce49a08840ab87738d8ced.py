def distance(strand1, strand2):
    distance = 0
    for nucleo1, nucleo2 in zip(strand1, strand2):
        if nucleo1 != nucleo2:
            distance += 1
    return distance
