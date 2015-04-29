def distance(s1, s2):
    return len(filter(lambda (l, r): l != r, zip(s1, s2)))
