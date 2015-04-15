def distance(dna1, dna2):
    assert(len(dna1) == len(dna2))
    return len([1 for x,y in zip(dna1, dna2) if x!=y])
