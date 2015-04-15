def distance(dna1, dna2):
    dist = 0
    for nuc1, nuc2 in zip(dna1, dna2):
        if nuc1 != nuc2:
            dist += 1

    return dist
