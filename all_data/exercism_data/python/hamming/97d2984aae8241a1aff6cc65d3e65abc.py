def distance(dna_x, dna_y):
    if len(dna_x) != len(dna_y):
        raise ValueError('Sequences must be of equal length!')

    dist = 0
    for x, y in zip(dna_x, dna_y):
        if x != y:
            dist += 1

    return dist
