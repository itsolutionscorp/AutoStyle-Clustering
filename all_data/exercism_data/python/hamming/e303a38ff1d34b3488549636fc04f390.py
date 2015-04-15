def hamming(dna1, dna2):
    hamming = abs(len(dna1)-len(dna2))
    
    length = min(len(dna1), len(dna2))
    for i in range(length):
        if dna1[i] != dna2[i]:
            hamming += 1
    
    return hamming
