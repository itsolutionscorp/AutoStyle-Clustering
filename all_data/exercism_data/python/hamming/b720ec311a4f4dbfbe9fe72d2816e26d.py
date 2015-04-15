def distance(a, b):
    if len(a) != len(b):
        raise ValueError
    return sum(i != j for i, j in zip(a, b))
