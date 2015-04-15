def distance(genom_a, genom_b):
    dist = 0
    for a, b in zip(genom_a, genom_b):
        dist += (a != b)
    return dist
