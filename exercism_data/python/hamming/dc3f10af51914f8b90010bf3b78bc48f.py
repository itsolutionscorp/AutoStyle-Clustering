def hamming(dna_1, dna_2):
    
    # check the respective lengths of the strands
    # initialize the return value to the difference
    ham = len(dna_1) - len(dna_2)
    max_len = 0
    
    # dna_2 is longer
    if ham < 0:
        ham = ham * -1
        max_len = len(dna_1)
    # dna_1 is longer
    else:
        max_len = len(dna_2)
        
    # check to see if there are differences within the dna
    for i in range(0, max_len):
        if dna_1[i] != dna_2[i]:
            ham += 1           
    
    return ham
