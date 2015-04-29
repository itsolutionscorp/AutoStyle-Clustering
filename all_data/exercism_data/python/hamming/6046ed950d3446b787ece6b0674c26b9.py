def hamming(dna_1, dna_2):
    count = abs(len(dna_1)-len(dna_2))
    for i in xrange(len(min([dna_1, dna_2], key=len))):
        if dna_1[i] != dna_2[i]:
            count += 1
    return count
