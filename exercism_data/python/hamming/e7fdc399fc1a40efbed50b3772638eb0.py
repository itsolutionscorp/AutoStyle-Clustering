def distance(A, B):
    return sum(a != b for a, b in zip(A, B))
