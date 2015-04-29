def distance(x, y):
    return sum(1 for z in zip(x, y) if z[0] != z[1])
