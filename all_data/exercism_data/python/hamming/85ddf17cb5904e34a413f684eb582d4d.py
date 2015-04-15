from itertools import zip_longest


def hamming(a, b):
    return sum(x != y for (x, y) in zip_longest(a, b))
