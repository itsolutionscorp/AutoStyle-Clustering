def hamming(dna1, dna2):
    length = min(len(dna1), len(dna2))
    hamming = max(len(dna1), len(dna2))-length
    
    for i in range(length):
        nucleotide1 = dna1[i]
        nucleotide2 = dna2[i]
        if nucleotide1 <> nucleotide2:
            hamming += 1
    
    return hamming
