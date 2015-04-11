from functools import reduce
from operator import mul


def slices(d, n):
    if n > len(d):
        raise ValueError
    return list(map(
        lambda x: list(map(int, list(d[x:x+n]))),
        range(len(d)-n+1)))


def largest_product(d, n):
    if n == 0:
        return 1  # I'm not sure I agree
    return max(map(lambda x: reduce(mul, x), slices(d, n)))
