#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python series exercise.
#
# v1: Using functools.reduce() and operator.mul()

from functools import reduce
from operator import mul


def largest_product(digits, count):
    """
    Calculate the largest product for a series of consecutive digits.
    """

    if len(digits) == 0:
        return 1

    products = [prod(x) for x in slices(digits, count)]
    return max(products)


def slices(digits, count):
    """
    Return a list of ``count``-length slices from an integer iterable.
    """

    if not 0 < count <= len(digits):
        raise ValueError('{} is not a valid slice length for a series '
                         'of length {}.'.format(count, len(digits)))

    last = len(digits)-count+1

    series = []

    for start in range(last):
        substring = digits[start:start+count]
        series.append([int(x) for x in substring])

    return series


def prod(iterable):
    """
    Return the product of all numbers in an iterable.
    """
    return reduce(mul, iterable)
