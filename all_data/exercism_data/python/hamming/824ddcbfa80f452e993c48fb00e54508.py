def distance(dna1, dna2):
    count = 0
    for index in range(0, len(dna1)):
        if dna1[index] != dna2[index]:
            count += 1
    return count
