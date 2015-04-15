def hamming(firstStrand, secondStrand):
    return sum(map(lambda x,y: x != y, firstStrand, secondStrand))
