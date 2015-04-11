from operator import mul
from functools import reduce

def slices(digits, n):
    if not n or n > len(digits):
        raise ValueError(
            'Unable to create slices of {} length'.format(n)
        )
    digits = [int(digit) for digit in digits]
    series = []
    for i, digit in enumerate(digits):
        s = digits[i:i+n]
        if len(s) == n:
            series.append(s)
    return series

def largest_product(digits, n):
    if not digits or not n:
        return 1
    return max(
        reduce(mul, chunk, 1)
        for chunk in slices(digits, n)
    )
