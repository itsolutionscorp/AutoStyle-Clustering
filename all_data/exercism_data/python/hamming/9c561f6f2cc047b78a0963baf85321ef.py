def distance(dna1, dna2):
    if len(dna1) == len(dna2):
        return sum([x != y for x, y in zip(dna1, dna2)])
    return -1
