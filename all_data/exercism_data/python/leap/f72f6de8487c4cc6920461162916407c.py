"""
Solution to exercism leap problem.
"""


def _divisible(year, denominator):
    """ Is `year` divisible by `denominator`? """
    return year % denominator == 0


def is_leap_year(year):
    """ Determine if `year` is a leap-year. """
    return (
        _divisible(year, 4) and not _divisible(year, 100)
        or _divisible(year, 400)
    )
