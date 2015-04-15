def distance(a, b):
    if len(a) != len(b):
        raise ValueError
    return sum(1 for a, b in zip(a, b) if a != b)
