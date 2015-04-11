def distance(dnaStrand, otherDnaStrand):
    hammingDistance = 0
    for index in range(len(dnaStrand)):
        if dnaStrand[index] != otherDnaStrand[index]:
            hammingDistance += 1
    return(hammingDistance)
