def distance(s1, s2):
    if len(s1) == len(s2):
        return sum([s1[i]!=s2[i] for i in range(len(s1))])
    return -1
