from operator import mul
from functools import reduce


def slices(digits, length):
    if length > len(digits):
        raise ValueError(
            'invalid input. requested length longer than given digits')
    if length < 0:
        raise ValueError('invalid input. can\'t calculate negative lengths.')
    values = []
    for i in range(len(digits) - length + 1):
        values.append([int(x) for x in digits[i:i + length]])
    return values


def largest_product(digits, length):
    return max([reduce(mul, values, 1) for values in slices(digits, length)])
