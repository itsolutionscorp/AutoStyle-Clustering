def distance(dna_x, dna_y):   

    idx = 0
    count = len(dna_x)

    dist = 0
    while idx < count:
        if dna_x[idx] != dna_y[idx]:
            dist += 1
        idx += 1

    return dist
