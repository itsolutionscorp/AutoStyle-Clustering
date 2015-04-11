def distance(s1, s2):
    return sum([0 if c1 == c2 else 1 for (c1,c2) in zip(list(s1),list(s2))])
