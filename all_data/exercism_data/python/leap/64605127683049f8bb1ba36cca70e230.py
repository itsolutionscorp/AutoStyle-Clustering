#!/usr/bin/env python

"""Year"""

__author__ = u'David Muse'
__date__ = u'2014-09-24'
__version__ = u'0.0.1'

def is_leap_year(year):
    """
    @param year Integer
    @returns Boolean
    @brief is_leap_year
    """

    if year % 400 == 0: # year is evenly divisible by 400
        return True

    elif year % 100 == 0: # year is evenly divisible by 100
        return False

    elif year % 4 == 0: # year is evenly divisible by 4
        return True

    else: # Default Case - not a leap year
        return False
