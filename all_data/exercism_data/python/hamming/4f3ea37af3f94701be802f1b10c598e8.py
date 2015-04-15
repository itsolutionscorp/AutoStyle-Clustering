def hamming(firstStrand, secondStrand):
    return sum(map(lambda x,y: int(x != y), firstStrand, secondStrand))
