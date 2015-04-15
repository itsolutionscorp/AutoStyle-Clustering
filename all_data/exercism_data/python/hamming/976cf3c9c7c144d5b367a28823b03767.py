def distance(source, target):
    return sum(s != t for s, t in zip(source, target))
