def distance(s1, s2):
    d = 0
    for i in range(len(s1)):
        if s1[i] != s2[i]: d += 1
    return d
