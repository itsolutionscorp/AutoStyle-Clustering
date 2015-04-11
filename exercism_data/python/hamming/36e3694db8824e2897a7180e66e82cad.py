def hamming(strand1, strand2):
    difference = 0

    # if of unequal lengths, all extra letters are assumed
    # to be different
    if len(strand2) != len(strand1):
        difference += abs(len(strand1) - len(strand2))
        # save shorter strand as strand1 so we can iterate without error
        if len(strand2) < len(strand1):
            temp_strand = strand1
            strand1 = strand2
            strand2 = temp_strand

    for i in range(len(strand1)):
        difference += (strand1[i] != strand2[i])

    return difference
        
