def distance(string1, string2):
    distance = 0
    for i in range(0, len(string1)):
        if string1[i]!=string2[i]: distance += 1
    return distance
