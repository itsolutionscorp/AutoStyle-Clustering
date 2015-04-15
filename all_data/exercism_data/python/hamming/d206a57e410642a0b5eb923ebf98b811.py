def distance(dna1, dna2):
    counter = 0
    for i in xrange(min(len(dna1), len(dna2))):
        if dna1[i] != dna2[i]:
            counter += 1
    return counter
