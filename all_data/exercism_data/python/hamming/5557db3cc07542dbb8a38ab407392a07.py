def distance(dna1, dna2):
    count = 0
    for x, y in zip(dna1, dna2):
        if x != y:
            count += 1
    return count
