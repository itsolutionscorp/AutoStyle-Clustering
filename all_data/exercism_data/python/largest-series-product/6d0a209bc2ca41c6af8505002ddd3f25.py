__author__ = 'jimblackler'

from operator import mul


def slices(string, n):
    if n > len(string):
        raise ValueError()
    array = [int(x) for x in string]
    return [array[x - n: x] for x in xrange(n, len(array) + 1)]


def largest_product(string, n):
    if len(string) == 0:
        return 1
    return max([reduce(mul, x) for x in slices(string, n)])
