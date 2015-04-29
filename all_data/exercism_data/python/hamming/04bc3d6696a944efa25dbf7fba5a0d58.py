def distance(dna1,dna2):
    if len(dna1) != len(dna2): # Return 0 for non-comparable DNA strands
        return 0
    else:
        ham = 0 # The hamming distance will be stored in ham
        for i in range(len(dna1)):
            if dna1[i] != dna2[i]: # When nucleotides differ we increase ham by 1
                ham += 1
        return ham
