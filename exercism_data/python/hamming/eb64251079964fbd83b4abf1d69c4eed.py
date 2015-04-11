

def distance(dna1, dna2):
    distance = 0
    for index, n in enumerate(dna1):
        if n != dna2[index]:
            distance += 1
    return distance
