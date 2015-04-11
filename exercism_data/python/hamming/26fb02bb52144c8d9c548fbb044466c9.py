def distance(strand1, strand2):

    difference = 0
    for idx, nuc in enumerate(strand1):
        if nuc != strand2[idx]:
            difference += 1
    return difference
