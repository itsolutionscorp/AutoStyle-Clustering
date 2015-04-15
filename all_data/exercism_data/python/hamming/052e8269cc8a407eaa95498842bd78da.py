def hamming(strand1, strand2):
    hamnum = 0
    for i in range(min(len(strand1), len(strand2))):
        if strand1[i] != strand2[i]:
            hamnum += 1
            
    return hamnum + abs(len(strand1) - len(strand2))
