"""Tests for the largest-series-product exercise

Implementation note:
In case of invalid inputs to the 'slices' or 'largest_product' functions
your program should raise a ValueError with a meaningful error message.

Feel free to reuse your code for the series exercise!
"""
import unittest


def prod(numbers):
    result = 1
    for n in numbers:
        result *= n
    return result

def largest_product(digits, number):
    return max(prod(s) for s in slices(digits, number))
    
def slices(digits, number):
    if len(digits) < number:
        raise ValueError
    return [[int(x) for x in digits[n:n+number]] for n in xrange(len(digits) - number + 1)]
