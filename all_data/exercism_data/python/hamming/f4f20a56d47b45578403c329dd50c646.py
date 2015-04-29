def distance(dna1,dna2):
    # Counts differences between 2 dna strands: dna1, dna2
    diff = 0
    for i in range(len(dna1)):
        if dna1[i] != dna2[i]:
            diff +=1
    return diff
