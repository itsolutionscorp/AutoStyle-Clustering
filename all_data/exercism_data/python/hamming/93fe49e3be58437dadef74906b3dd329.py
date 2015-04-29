def hamming(dna1, dna2):
    diff = 0
    for i in range(max(len(dna1), len(dna2))):
        try:
            if dna1[i] != dna2[i]:
                diff += 1
        except IndexError:
            diff += 1
    return diff
