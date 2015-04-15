def distance(strand1, strand2):
    distance = 0
    for i, letter in enumerate(strand1):
        if letter.upper() != strand2[i].upper():
            distance += 1
    return distance
