def distance(a, b):
    if len(a) != len(b):
        raise ValueError
    return sum(1 for i in xrange(len(a)) if a[i] != b[i])
