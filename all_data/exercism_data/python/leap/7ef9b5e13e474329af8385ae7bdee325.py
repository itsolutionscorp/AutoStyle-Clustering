# -*- coding: utf-8 -*-
"""
Created on Tue Sep 30 13:13:43 2014

@author: klin
"""

def is_leap_year(year):
    return year % 4 == 0  and   (year % 25 != 0  or  year % 400 == 0)
