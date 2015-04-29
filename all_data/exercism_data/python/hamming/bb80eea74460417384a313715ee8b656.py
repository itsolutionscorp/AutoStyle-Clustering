def distance(s1, s2):
    ham = 0
    for i in xrange(len(s1)):
        base_pair = s1[i]
        comp = s2[i]
        if base_pair != comp:
            ham += 1
    return ham
