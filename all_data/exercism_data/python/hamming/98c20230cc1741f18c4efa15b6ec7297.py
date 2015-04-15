from itertools import izip

def hamming(firstStrand, secondStrand):
    distance = abs(len(firstStrand) - len(secondStrand))
    for first, second in izip(firstStrand,secondStrand):
        if first != second: distance += 1
    return distance
