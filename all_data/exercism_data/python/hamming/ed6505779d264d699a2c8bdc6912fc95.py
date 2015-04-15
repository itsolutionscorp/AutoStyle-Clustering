def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        return -1

    if dna1 == dna2:
        return 0

    return sum([dna1[i] != dna2[i] for i in range(len(dna1))])
