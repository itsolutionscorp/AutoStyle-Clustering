def hamming(dna1, dna2):
    return abs(len(dna1)-len(dna2))+[i!=j for (i,j) in zip(dna1,dna2)].count(True)
