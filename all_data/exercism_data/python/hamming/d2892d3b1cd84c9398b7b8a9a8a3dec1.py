def distance(dna1, dna2):
    count = 0
    if len(dna1) < len(dna2):
        for x in range(0, len(dna1)):
            if dna1[x] != dna2[x]:
                count += 1
    else:
        for x in range(0, len(dna2)):
            if dna1[x] != dna2[x]:
                count += 1
    return count
