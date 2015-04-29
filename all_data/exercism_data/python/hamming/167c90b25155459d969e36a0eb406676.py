def distance(dna1, dna2):
    difference = 0
    for i, c in enumerate(dna1):
        if c != dna2[i]:
            difference += 1
    return difference
