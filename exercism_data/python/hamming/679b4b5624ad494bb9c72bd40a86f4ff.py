def distance(strand1, strand2):
    distance = 0
    for pair in zip(strand1, strand2):
        if pair[0] != pair[1]:
            distance += 1
            
    return distance