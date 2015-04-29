from itertools import zip_longest
from functools import reduce

def increment(accum, iterable):
    return accum + 1 if iterable[0] != iterable[1] else accum

def hamming(first, second):
    return reduce(increment, zip_longest(first, second), 0)
