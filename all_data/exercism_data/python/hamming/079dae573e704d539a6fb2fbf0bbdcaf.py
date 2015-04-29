def distance(dna1, dna2):
    index =0
    hammingdistance = 0
    for c in dna1:
        if c != dna2[index]:
            hammingdistance = hammingdistance + 1
        index = index + 1
    return hammingdistance
