# -*- coding: utf-8 -*-


def is_leap_year(year):
    """ Takes a year and report if it is a leap year.

    :param year: the year
    :type year: int
    :return: True if the year is a leap year. False if not
     :rtype: Boolean
    """
    if (year % 4 == 0 and year % 100 != 0) or \
            (year % 4 == 0 and (year % 100 == 0 and year % 400 == 0)):
        return True
    return False
