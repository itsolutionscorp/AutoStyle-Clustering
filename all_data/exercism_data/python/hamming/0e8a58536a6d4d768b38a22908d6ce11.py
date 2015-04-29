def hamming(strand_1, strand_2):

    # Length checking
    len_1 = len(strand_1)
    len_2 = len(strand_2)

    if len_1 > len_2:
        hamming = len_1 - len_2
        strand_1 = strand_1[:-hamming]

    elif len_2 > len_1:
        hamming = len_2 - len_1
        strand_2 = strand_2[:-hamming]

    else:
        hamming = 0


    for i, n in enumerate(strand_1):

        if n != strand_2[i]:
            hamming += 1


    return hamming
