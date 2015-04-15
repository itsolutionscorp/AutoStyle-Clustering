def distance(a, b):
    counter = 0
    for k in range(0, len(a)):
        if a[k] != b[k]:
            counter += 1
    return counter
