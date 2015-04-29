def distance(dna1, dna2):
    dist = 0
    for index, el in enumerate(dna1):
        if el != dna2[index]:
            dist += 1
    return dist
