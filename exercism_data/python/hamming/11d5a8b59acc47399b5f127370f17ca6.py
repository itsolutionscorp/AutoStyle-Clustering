def distance(dna1,dna2):
    counter = 0
    for index, char in enumerate(dna1):
        if(char != dna2[index]):
            counter +=1
    return counter
