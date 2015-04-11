def distance(x, y):
    z = zip(x, y)
    return len(filter(lambda n: n[0] != n[1], z))
