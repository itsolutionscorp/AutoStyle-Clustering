def distance(dna1, dna2):
    return sum( 1 for a,b in zip(dna1,dna2) if a != b)
