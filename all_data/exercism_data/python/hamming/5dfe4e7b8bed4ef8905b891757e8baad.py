def distance(seqA, seqB):
    dist = 0
    for index, char in enumerate(seqA):
        if char != seqB[index]:
            dist += 1

    return dist
