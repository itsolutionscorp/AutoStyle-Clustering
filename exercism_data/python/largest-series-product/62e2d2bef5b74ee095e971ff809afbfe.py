"""
series.py: a program that, when given a string of digits, can
calculate the largest product for a series of consecutive digits of length n.
"""
from operator import mul
from functools import reduce


def slices(series, number):
    """
    Returns a list of lists containing possible consecutive number series
    of a series with length determined by number.
        series: a string containing a consecutive number series
        number: the length of the possible series combinations
    """
    if number == 0 or number > len(series):
        raise ValueError
    digits = [int(i) for i in series]
    return [digits[i:i + number] for i in range(len(digits) - number + 1)]


def _product(numbers):
    """
    Returns the product of a list of numbers.
        numbers: a list of integers
    """
    return reduce(mul, numbers)


def largest_product(digits, length):
    """
    Returns the largest product for a series of consecutive digits of length n.
        digits: a series of numbers as a string
        length: the length of the consective digits
    """
    if length == 0:
        return 1
    return max(_product(s) for s in slices(digits, length))
