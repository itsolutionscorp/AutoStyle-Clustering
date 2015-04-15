def distance(dna1, dna2):
    return sum([
        int(dna1[i] != dna2[i])
        for i in range(len(dna1))
    ])
