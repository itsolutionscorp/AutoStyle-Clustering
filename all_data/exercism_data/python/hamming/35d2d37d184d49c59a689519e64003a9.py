def distance(s1, s2):
    return len(filter(lambda t: t[0] != t[1], zip(s1, s2)))
