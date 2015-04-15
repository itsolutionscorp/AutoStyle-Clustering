def hamming(strand1, strand2):
    difference = 0
    len_1, len_2 = len(strand1), len(strand2)
    # if of unequal lengths, all extra letters are assumed
    # to be different
    if len_1 != len_2:
        difference += abs(len_1 - len_2)

    for i in range(min(len_1, len_2)):
        difference += (strand1[i] != strand2[i])

    return difference
        
