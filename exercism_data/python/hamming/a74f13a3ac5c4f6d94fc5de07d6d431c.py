def distance(dna1, dna2):
    dist = 0
    for a, b in zip(dna1, dna2):
        if a != b:
            dist += 1
            
    return dist
