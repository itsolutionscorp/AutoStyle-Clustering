def distance(dna1, dna2):
    return sum([
        int(nuc1 != nuc2)
        for nuc1, nuc2 in zip(dna1, dna2)
    ])
