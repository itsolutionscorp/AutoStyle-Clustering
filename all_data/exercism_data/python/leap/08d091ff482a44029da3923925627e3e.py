#!/usr/bin/env python

def is_leap_year(year):
    """Returns true if the input year is a leap year."""
    if year % 4 == 0:
        if (year % 100 == 0) and (year % 400 != 0):
            return False
        return True
    else:
        return False
