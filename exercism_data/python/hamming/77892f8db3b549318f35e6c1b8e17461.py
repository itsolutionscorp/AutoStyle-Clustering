def distance(dna1, dna2):
    dist = 0
    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            dist += 1
    return dist
