def distance(a, b):
    distance = 0
    for i in range(0, len(a)):
        if a[i] != b[i]:
            distance += 1
    return distance
