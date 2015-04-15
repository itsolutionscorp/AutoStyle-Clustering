def distance(s1, s2):
    return sum(1 for (a,b) in zip(s1, s2) if a!=b)
