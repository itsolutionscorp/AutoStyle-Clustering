def distance(strand1, strand2):
    distance = 0
    for index, nucleotide in enumerate(strand1):
        if nucleotide != strand2[index]:
            distance += 1
    return distance
