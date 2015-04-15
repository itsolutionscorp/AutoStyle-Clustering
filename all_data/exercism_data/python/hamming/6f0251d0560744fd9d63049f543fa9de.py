def hamming(dna1, dna2):
    i = 0
    count = 0
    if len(dna1) > len(dna2):
        set1=dna2
        set2=dna1
        count = len(dna1) - len(dna2)
    else:
        set1=dna1
        set2=dna2
        count = len(dna2) - len(dna1)
    for c in set1:
        if c != set2[i]:
            count += 1
        i += 1
    return count
