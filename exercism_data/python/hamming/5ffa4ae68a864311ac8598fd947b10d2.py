def distance(dna1, dna2):
    return len([(a,b) for (a,b) in zip(dna1,dna2) if a != b])
