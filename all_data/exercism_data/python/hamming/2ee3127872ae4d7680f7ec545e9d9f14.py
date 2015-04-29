def distance(a, b):
    if len(a) != len(b):
        raise ValueError
    return sum(1 for i, j in zip(a, b) if i != j)
