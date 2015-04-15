def distance(dna1, dna2):
    result = 0
    for i in range(0, min(len(dna1), len(dna2))):
        if dna1[i] != dna2[i]:
            result += 1
    return result
