#!/usr/bin/python

import calendar

def is_leap_year(year):
    if calendar.isleap(year):
        return True
    else:
        return False
