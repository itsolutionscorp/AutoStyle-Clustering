def hamming(dna1, dna2):
    if len(dna1)>len(dna2):
        longdna = dna1
        shortdna = dna2
    else:
        longdna = dna2
        shortdna = dna1

    counter = abs(len(dna1)-len(dna2))

    for i in range(0,len(shortdna)):
        if shortdna[i]!=longdna[i]:
            counter +=1

    return counter
