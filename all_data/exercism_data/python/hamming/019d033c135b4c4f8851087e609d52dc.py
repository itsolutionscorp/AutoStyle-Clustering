def distance(first, second):
    return len([x for x in zip(first, second) if x[0] != x[1]])
