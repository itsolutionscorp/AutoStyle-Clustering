def distance(left, right):
    return sum(l != r for l, r in zip(left, right))
