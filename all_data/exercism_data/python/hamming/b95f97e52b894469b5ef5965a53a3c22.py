def hamming(dna1, dna2):
    diff = 0
    len1 = len(dna1)
    len2 = len(dna2)
    min_len = min(len1, len2)
    #The length difference also contributes to hamming distance
    diff += abs(len1 - len2)
    for i in range(min_len):
        if dna1[i] != dna2[i]:
            diff += 1
    return diff
