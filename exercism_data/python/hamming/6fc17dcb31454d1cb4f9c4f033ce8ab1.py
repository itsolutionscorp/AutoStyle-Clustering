def distance(a, b):
    if len(a) != len(b):
        return None

    distance = 0

    for va, vb in zip(a, b):
        if va != vb:
            distance += 1

    return distance
