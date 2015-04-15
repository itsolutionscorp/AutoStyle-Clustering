def distance(dna_1, dna_2):
    dna_1 = dna_1.lower()
    dna_2 = dna_2.lower()
    distance = 0
    for i in range(0, len(dna_1)):
        if dna_1[i] != dna_2[i]:
            distance += 1
    return distance
