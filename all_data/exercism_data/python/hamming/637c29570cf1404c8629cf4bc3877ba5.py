def distance(dna1, dna2):
    counter = 0

    for x in range(0, len(dna1)):
        if dna1[x] != dna2[x]:
            counter += 1

    return counter
