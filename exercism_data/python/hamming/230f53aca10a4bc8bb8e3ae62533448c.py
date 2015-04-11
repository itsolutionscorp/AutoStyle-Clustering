def distance(a, b):
    return sum(i[0] != i[1] for i in zip(a, b))
