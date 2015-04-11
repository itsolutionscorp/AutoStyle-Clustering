def distance(s1, s2):
    result = 0

    for i in xrange(len(s1)):
        if s1[i] != s2[i]:
            result += 1

    return result
