from functools import reduce
from operator import mul


def slices(d, n):
    if n > len(d):
        raise ValueError
    return [[int(y) for y in list(d[x:x+n])]
            for x in range(len(d)-n+1)]


def largest_product(d, n):
    if n == 0:
        return 1  # I'm not sure I agree
    return max([reduce(mul, x) for x in slices(d, n)])
