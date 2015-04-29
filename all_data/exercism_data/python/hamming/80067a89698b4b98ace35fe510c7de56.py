def hamming(strand1, strand2):
    difference = 0

    # if of unequal lengths, all extra letters are assumed
    # to be different
    if len(strand2) != len(strand1):
        difference += abs(len(strand1) - len(strand2))

    for i in range(min(len(strand1), len(strand2))):
        difference += (strand1[i] != strand2[i])

    return difference
        
