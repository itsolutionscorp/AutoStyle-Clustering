"""Palindrome"""

from math import sqrt


def smallest_palindrome(max_factor, min_factor=1):
    """Return the smallest palindrome with factors in a given range.

    :param max_factor: the upper bound of factors
    :param min_factor: the lower bound of factors
    """
    for number in xrange(min_factor ** 2, max_factor ** 2 + 1):
        if is_palindrome(number):
            fs = factors(number, min_factor, max_factor)
            if fs is not None:
                return number, fs


def largest_palindrome(max_factor, min_factor=1):
    """Return the largest palindrome with factors in a given range.

    :param max_factor: the upper bound of factors
    :param min_factor: the lower bound of factors
    """
    for number in xrange(max_factor ** 2, min_factor ** 2 - 1, -1):
        if is_palindrome(number):
            fs = factors(number, min_factor, max_factor)
            if fs is not None:
                return number, fs


def is_palindrome(s):
    """Return true if palindrome."""
    s = str(s)
    return s == s[::-1]


def factors(number, lower=1, upper=None):
    """Return two factors in the lower and upper bounds.

    :param lower: the lower bound of factors
    :param upper: the upper bound of factors
    """
    if upper is None:
        upper = number
    for factor in xrange(lower, min(upper, int(sqrt(number) + 0.1)) + 1):
        quotient, remainder = divmod(number, factor)
        if remainder == 0 and lower <= quotient <= upper:
            return factor, quotient
