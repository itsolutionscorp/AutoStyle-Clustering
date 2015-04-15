def distance(x, y):
    if(len(x) != len(y)):
        raise ValueError('Strands must be the same length')

    d = 0
    for i, n in enumerate(x):
        d += y[i] is not n
    return d
