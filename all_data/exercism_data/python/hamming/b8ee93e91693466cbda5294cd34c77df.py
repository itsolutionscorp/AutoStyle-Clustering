def hamming(dna1, dna2):
    min_length = min(len(dna1), len(dna2))
    dist = max(len(dna1), len(dna2)) - min_length

    for i in xrange(min_length):
        if dna1[i] != dna2[i]:
            dist += 1

    return dist
