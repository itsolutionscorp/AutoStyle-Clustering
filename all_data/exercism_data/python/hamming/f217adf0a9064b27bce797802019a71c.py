def distance(dna1, dna2):
    d = 0
    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            d += 1
    return d
