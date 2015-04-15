#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python difference_of_squares exercise.
#
# v2: Using "x*x" instead of the slower "x**2" and remove left over
#     "pass" statement in difference()
# v1: Using sum(), abs(), range() and "**2"


def difference(length):
    """
    Return the (absolute) difference between the Square of Sums and the
    Sum of Squares in the interval [1, length].
    """
    return abs(square_of_sum(length) - sum_of_squares(length))


def square_of_sum(length):
    """
    Return the Square of Sums in the interval [1, length].
    """
    s = sum(range(1, length+1))
    return s*s


def sum_of_squares(length):
    """
    Return the sum of Squares in the interval [1, length].
    """
    return sum([x*x for x in range(1, length+1)])
