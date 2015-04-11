#! /usr/bin/env python

def is_leap_year(year):
    isLeapYear = False
    if (year % 4) == 0:
        if (year % 100) == 0:
            if (year % 400) == 0:
                isLeapYear = True
        else:
            isLeapYear = True
    return isLeapYear
