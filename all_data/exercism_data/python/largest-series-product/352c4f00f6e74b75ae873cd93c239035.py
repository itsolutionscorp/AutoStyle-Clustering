from operator import mul
from functools import reduce

def slices(string, n):
    """Returns all possible consecutive number sequences of length 'n' in a string"""
    if n < 1:
        raise ValueError('Slice length must be greater than 0')
    elif n > len(string):
        raise ValueError('Slice length must be less than or equal to string length')
    else:
        # this borders on unreadable .. perhaps worth splitting up across a few lines
        return [list(map(int, list(string[i:i+n]))) for i in range(len(string) - n + 1)]

def largest_product(series, n):
    """Returns largest product for a series slice of length n"""
    if not series:
        return 1
    else:
        products = sorted([reduce(mul, slice) for slice in slices(series, n)])
        return products[-1]
