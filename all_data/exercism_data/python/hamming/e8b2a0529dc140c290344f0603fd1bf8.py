def distance(x, y):
    dist = 0
    for i, j in zip(x, y):
        if i != j:
            dist = dist + 1
    return dist
