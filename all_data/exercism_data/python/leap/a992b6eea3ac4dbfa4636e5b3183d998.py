# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 01:00:38 2014

@author: laegrim
"""


def is_leap_year(year):
    '''
    If a year is evenly divisible by 4, but not by 100 unless it is also 
    evenly divisible by 400, then it is a leap year.
    '''
    return year % 4 == 0 and (year % 100 != 0 or (year % 400 == 0 and year % 100 == 0))
