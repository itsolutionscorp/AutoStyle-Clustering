# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 11:01:31 2014

@author: Blair
"""

def is_leap_year(year):
    if year %4 != 0:
        #print str(year) + " is not a leap year."
        return False
    if year %100 == 0 and year %400 != 0:
        #print str(year) + " is not a leap year."
        return False
    else:
        #print str(year) + " is a leap year."
        return True

        
        
