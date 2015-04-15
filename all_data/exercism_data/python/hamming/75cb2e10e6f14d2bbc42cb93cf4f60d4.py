def distance(dna1, dna2):
    result = 0
    for i in range (0, len(dna1)):
        if dna1[i] != dna2[i]:
            result +=1
            i +=1
        else:
            i +=1
    return result
