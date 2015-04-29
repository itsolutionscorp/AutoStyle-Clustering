def distance(s1, s2):
    if len(s1) != len(s2):
        raise ValueError('Hamming distance is only defined for same length strings')

    diff = 0
    for i in xrange(len(s1)):
        if s1[i] != s2[i]:
            diff += 1
    return diff
