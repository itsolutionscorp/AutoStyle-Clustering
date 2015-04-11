def distance(dna1, dna2):
    count = 0
    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            count += 1        
    return count
