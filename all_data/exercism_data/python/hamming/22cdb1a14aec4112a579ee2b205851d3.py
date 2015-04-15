def distance(string1, string2):
    distance = 0
    for pair in zip(string1, string2):
        if pair[0] != pair[1]:
            distance += 1

    return distance
