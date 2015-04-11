from operator import mod


def is_leap_year(year):
    """Verify if a year is a leap year.

    :year: an int object
    :returns: True or False

    """
    return not mod(year, 4) and not (not mod(year, 100) and mod(year, 400))
