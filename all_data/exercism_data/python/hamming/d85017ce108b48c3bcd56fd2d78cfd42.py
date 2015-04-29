def distance(a, b):
    return sum(a[i] != b[i] for i in range(len(min(a, b))))
