def distance(dna_a, dna_b):
    dist = 0
    for a, b in zip(dna_a, dna_b):
        if a != b:
            dist += 1
    return dist
