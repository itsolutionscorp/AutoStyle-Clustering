def hamming(dna, dna2):
    distance=0
    num=0
    while len(dna)>num:
        if dna[num] != dna2[num]:
            distance+=1
        num+=1
    return distance
