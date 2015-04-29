# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 16:49:14 2014

@author: rebuhr
"""

def is_leap_year(year):
    return (
        (year % 4 == 0) 
        and (year % 100 != 0) 
        or (year % 400 == 0))
