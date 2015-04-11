# -*- coding: utf-8 -*-


def is_leap_year(year):
    '''
    Given a year, determine if it is a leap year.
    '''
    if year % 4 == 0:
        if year % 100 == 0 and year % 400 != 0:
            return False
        else:
            return True
    else:
        return False
