def distance(base, other):
    return sum(n1 != n2 for n1, n2 in zip(base, other))
