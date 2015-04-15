def distance(x, y):
    dist = 0
    for i in range(0, len(x)):
        if x[i] != y[i]:
            dist = dist + 1
    return dist
