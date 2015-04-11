from functools import reduce
from operator import mul


def slices(digits, length):
    if not 1 <= length <= len(digits):
        raise ValueError(
            "{} is not a valid slice length for a series of length {}".format(
                length, len(digits)))

    digits = [int(d) for d in digits]
    return [digits[i:i + length]
            for i in range(len(digits) - length + 1)]


def _product(numbers):
    return reduce(mul, numbers)


def largest_product(digits, length):
    if length == 0:
        return 1
    return max(_product(s)
               for s in slices(digits, length))
