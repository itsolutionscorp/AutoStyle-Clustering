__author__ = 'jimblackler'


def is_leap_year(year):
    """ Calculates if the specified year is a leap year in the Gregorian
    calendar.

    :param year: The year to test.
    :returns True if the specified year is a leap year.
    """

    # A leap year occurs if the year is evenly divisible by 400..
    if year % 400 == 0:
        return True
    # .. but not if evenly divisible by 100.
    if year % 100 == 0:
        return False
    # Otherwise, if evenly divisible by 4.
    return year % 4 == 0
