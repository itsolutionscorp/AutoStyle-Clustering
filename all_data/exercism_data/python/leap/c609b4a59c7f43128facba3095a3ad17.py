# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 16:49:14 2014

@author: rebuhr
"""

# program that tells you if a given year will be a Leap Year

def is_leap_year(year):
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    else:
        return False
