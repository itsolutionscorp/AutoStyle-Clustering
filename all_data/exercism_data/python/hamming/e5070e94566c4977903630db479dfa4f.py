def distance(a, b):
    return sum([1 for p in zip(a, b) if p[0] != p[1]])
