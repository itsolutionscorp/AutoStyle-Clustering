def distance(a, b):
    if len(a) != len(b):
        raise ValueError

    d = 0
    for i in xrange(len(a)):
        if a[i] != b[i]:
            d += 1
    return d
