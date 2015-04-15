__author__ = 'jetties'


def is_leap_year(year):
    """
    Whether the given number is a leap year.
    A leap occurs on every year that is evenly divisible by 4 except every year that is evenly divisible by 100,
    unless the year is also evenly divisible by 400
    :return: True if the year is a leap year.
    """
    if year % 100 == 0:
        return year % 400 == 0
    return year % 4 == 0
