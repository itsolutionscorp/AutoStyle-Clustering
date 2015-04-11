#!/usr/bin/env python

def is_leap_year(year):
    """Determines if a year is a leapyear."""
    return year % 4==0 and (not year % 100 == 0 or year % 400 ==0 )
