# -*- coding: utf-8 -*-
"""
Created on Sun Nov 02 16:29:28 2014

@author: Richard
"""


def is_leap_year(year):
# there are more years that aren't leap years than are leap-years, so the 
# default is False
    leap = False
    if type(year) is int or type(year) is float:
        if year % 4 == 0:
            leap = True
            if year % 100==0 and year % 400 !=0:
                leap = False
        return leap
    else: print "Invalid input! Please give only a number."  
