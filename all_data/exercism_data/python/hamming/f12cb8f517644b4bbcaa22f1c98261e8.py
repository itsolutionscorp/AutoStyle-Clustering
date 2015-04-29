def distance(a, b):
    difference = 0

    for i in range(min(len(a), len(b))):
        if b[i] != a[i]:
            difference += 1

    return difference
