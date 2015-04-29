def distance(StrandA,StrandB):
    distance = 0
    Strand = range(len(StrandA))
    for position in Strand:
        if StrandA[position] == StrandB[position]:
            distance = distance
        else: distance+=1
    return distance
