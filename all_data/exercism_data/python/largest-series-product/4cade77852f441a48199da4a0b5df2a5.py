from functools import reduce
from operator import mul


def slices(line, n):
    if n > len(line) or n < 0:
        raise ValueError('No such slice!')

    ilst = [int(s) for s in line]
    return [list(ilst[i:n + i]) for i in range(len(line) - n + 1)]


def largest_product(line, n):
    return max(reduce(mul, slc, 1) for slc in slices(line, n))
