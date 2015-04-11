def hamming(dna1, dna2):
    count = 0
    for i in range(min(len(dna1), len(dna2))):
        if dna1[i] != dna2[i]:
            count += 1
    return count + abs(len(dna1) - len(dna2))
