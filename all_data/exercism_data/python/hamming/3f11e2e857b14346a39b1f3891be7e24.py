def distance(s1, s2):
    i = 0
    distance = 0
    for base in s1:
        if base != s2[i]:
            distance += 1
        i += 1
    return distance
