def distance(s1, s2):
    i = 0
    distance = 0
    for n in s1:
        if n != s2[i]:
            distance += 1
        i += 1
    return distance
