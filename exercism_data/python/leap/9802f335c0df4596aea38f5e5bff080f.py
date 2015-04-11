# -*- coding: utf-8 -*-
"""
Created on Sat Nov 22 15:59:40 2014


"""

def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
    
 
  
print is_leap_year(1996)
