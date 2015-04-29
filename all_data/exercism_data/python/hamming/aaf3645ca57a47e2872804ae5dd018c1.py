def distance(dna1, dna2):
    dist = 0
    for index in range(len(dna1)):
        if (dna1[index] != dna2[index]):
            dist += 1
    return dist
