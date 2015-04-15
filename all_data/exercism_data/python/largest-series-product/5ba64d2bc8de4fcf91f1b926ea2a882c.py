from functools import reduce
from operator import mul


def largest_product(s, size):
    if size == 0:
        return 1
    return max(reduce(mul, x, 1) for x in slices(s, size))


def slices(s, size):
    if len(s) < size or size == 0:
        raise ValueError

    nums = list(map(int, s))
    rv = []

    while size <= len(nums):
        rv.append(nums[:size])
        del nums[0]

    return rv
