def distance(dna1,dna2):
    count = 0
    for x in range(len(dna1)):
        if dna1[x] != dna2[x]:
            count += 1
            
    return count
