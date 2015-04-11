"""
This file, difference_of_squares.py, contains three functions. These functions
involve the steps to obtain the difference of squares.  This is defined as the
difference between the square of the sum of positive integers smaller than x
and the sum of the squares of all the positive integers smaller than x.

Created for Exercism.io
"""

def square_of_sum(x):
    """
    This function takes in x, a positive integer, and returns the
    square of the sum of all positive integers from 1 to x.
    """
    total = 0
    for i in xrange(1, x + 1):
        total += i
    return total**2

def sum_of_squares(x):
    """
    This function takes in x, a positive integer, and returns the
    sum of the squares of all positive integers from 1 to x.
    """
    total = 0
    for i in xrange(1, x + 1):
        total += (i**2)
    return total

def difference(x):
    """
    This function takes in x, a positive integer, and returns the
    difference of the square of the sum and the sum of the squares
    for all the positive integers from 1 to x.
    """
    return square_of_sum(x) - sum_of_squares(x)
