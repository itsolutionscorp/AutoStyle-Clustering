# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 17:41:41 2014

@author: johann
"""

def is_leap_year(year):
    if year % 4 != 0:
        return False
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    return True
