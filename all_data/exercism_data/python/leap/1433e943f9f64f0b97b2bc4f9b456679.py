__author__ = 'James'


def is_leap_year(year):
    """
    Determine if year provided is a leap year.
    :param year: Year to check
    :return:Boolean if year is a leap year.
    """
    return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
