def distance(strand1, strand2):
    distance = 0
    for index, char in enumerate(strand1):
        if strand2[index] != char:
            distance += 1
            
    return distance
