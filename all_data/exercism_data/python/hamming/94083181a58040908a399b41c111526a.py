def distance(s1, s2):
    if len(s1) != len(s2):
        raise Exception('Strings not of same length. Aborting')
    dist = 0
    for x in range(len(s1)):
        if s1[x] != s2[x]:
            dist += 1
    return dist
