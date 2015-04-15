def hamming(s1,s2):

    return len(filter(lambda c: c[0]!=c[1], zip(s1,s2))) + max(len(s1),len(s2)) - min(len(s1),len(s2))
