def distance(a, b):
    return sum(1 if a[i] != b[i] else 0 for i in range(len(a)))
