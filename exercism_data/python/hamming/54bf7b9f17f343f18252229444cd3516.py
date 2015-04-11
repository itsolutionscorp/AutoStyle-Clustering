def hamming(dna_1, dna_2):
    
    # check the respective lengths of the strands
    # initialize the return value to the difference
    ham = len(dna_1) - len(dna_2)
    
    # dna_2 is longer
    if ham < 0:
        ham = ham * -1
        for i in range(0, len(dna_1)):
            if dna_1[i] != dna_2[i]:
                ham += 1
    # dna_1 is longer
    else:
        for i in range(0, len(dna_2)):
            if dna_1[i] != dna_2[i]:
                ham += 1
                
    return ham
