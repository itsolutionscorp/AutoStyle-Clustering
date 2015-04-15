def hamming(dna0, dna1):
    dna0_len = len(dna0)
    dna1_len = len(dna1)

    if dna0_len == 0:
        return dna1_len
    elif dna1_len == 0:
        return dna0_len

    count = 0
    dna_len = 0

    if dna0_len > dna1_len:
        count = dna0_len-dna1_len
        dna_len = dna1_len
    elif dna1_len > dna0_len:
        count = dna1_len-dna0_len
        dna_len = dna0_len
    else:
        dna_len = dna0_len
    
    for x in xrange(dna_len):
        if dna0[x] != dna1[x]:
            count += 1

    return count
