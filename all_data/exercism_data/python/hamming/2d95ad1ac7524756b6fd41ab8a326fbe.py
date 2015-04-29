def hamming(dna1, dna2):
    hamming_distance = 0
    for i in xrange(max(len(dna1),len(dna2))):
        try:
            if dna1[i] != dna2[i]:
                hamming_distance += 1
        except IndexError:
            hamming_distance += 1
    return hamming_distance
