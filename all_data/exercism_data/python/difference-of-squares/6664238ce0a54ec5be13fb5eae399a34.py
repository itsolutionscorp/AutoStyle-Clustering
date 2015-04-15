#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Difference of squares
"""

__version__ = "0.0.1"
__all__ = ["__version__", "difference", "sum_of_squares", "square_of_sum"]


def difference(number):
    """The difference of sum of squares and
    square of sum for the first `number` natural
    numbers

    .. versionadded:: 0.0.1

    :param number: the nth number
    """
    return square_of_sum(number) - sum_of_squares(number)


def sum_of_squares(number):
    """The sum of the squares of the first
    `number` natural numbers

    .. versionadded:: 0.0.1

    :param number: the nth number
    """
    return sum([n**2 for n in xrange(number+1)])


def square_of_sum(number):
    """The square of the sum of the first
    `number` natural numbers

    .. versionadded:: 0.0.1

    :param number: the nth number
    """
    return sum([n for n in xrange(number+1)]) ** 2
