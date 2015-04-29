

def distance(first, second):
    return sum([x != y for (x, y) in zip(first, second)])
