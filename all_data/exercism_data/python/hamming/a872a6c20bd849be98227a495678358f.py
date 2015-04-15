def distance(origin, mutation):
    count = 0
    for orig, mut in zip(origin, mutation):
        if orig != mut:
            count += 1
    return count
