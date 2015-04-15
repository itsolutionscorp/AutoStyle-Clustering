def hamming(x, y):
    dist = 0
    x_offset = max(len(x) - len(y), 0)
    y_offset = max(len(y) - len(x), 0)
    for i in range(0, min(len(x), len(y))):
        if x[i + x_offset] != y[i + y_offset]:
            dist += 1
    return dist
