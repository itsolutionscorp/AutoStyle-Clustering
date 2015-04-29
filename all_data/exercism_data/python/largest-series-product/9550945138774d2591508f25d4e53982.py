from operator import mul
from functools import reduce


def slices(digits, n):
    m = len(digits)
    if n == 0 or n > m: raise ValueError
    return [[int(digit) for digit in digits[i:i + n]]
            for i in range(m - n + 1)]


def largest_product(digits, n):
    if not digits:
        return 1
    return max(reduce(mul, (int(digit) for digit in group))
               for group in slices(digits, n))
