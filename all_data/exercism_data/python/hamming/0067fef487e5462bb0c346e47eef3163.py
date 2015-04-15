def distance(strand1, strand2):
    distance = 0
    if len(strand1) is len(strand2):
        for count,item in enumerate(strand1):
            if item is not strand2[count]:
                distance += 1
    return distance
