def distance(dna1, dna2):
    hamming = 0
    
    for i, acid in enumerate(dna1):
        if acid != dna2[i]:
            hamming += 1
    
    return hamming
