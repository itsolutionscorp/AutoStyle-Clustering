# -*- coding: utf-8 -*-
"""
year.py
Contains function is_leap_year(year) which tests whether a year is a leap year 
or not.
Created on Sat Oct 11 23:48:52 2014
"""

"""is_leap_year(year)
Tests whether it is a leap year or not
"""
def is_leap_year(year):
    #by definition, if the year is evenly divisible by 400, then it's a leap year
    if year%400==0:
        return True
    #by definition, if the year is evenly divisible by 100 and not 400 then it's not a leap year
    elif year%100==0:
        return False
    #by definition, if the year isn't evenly divisible by 100 but is by 4, then it's a leap year
    elif year%4==0:
        return True
    #otherwise, it's not a leap year
    else:
        return False
