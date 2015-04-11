"""year.py - take a year and report if it is a leap year."""


def is_leap_year(year):
    """Take a year and report if it is a leap year."""
    return ((year % 4 == 0) and (year % 100 != 0)) or (year % 400 == 0)
