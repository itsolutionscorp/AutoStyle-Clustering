def distance(a, b):
    d = 0
    for i in range(len(a)):
        d += a[i] != b[i]
    return d
