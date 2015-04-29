def hamming(dna1, dna2):
    hamming = 0.0
    lendna1 = len(dna1)
    lendna2 = len(dna2)
    short_strand = min(lendna1, lendna2)
    for i in range(short_strand):
        if dna1[i] != dna2[i]:
            hamming += 1
    hamming += abs(lendna1-lendna2)
    return hamming
            
