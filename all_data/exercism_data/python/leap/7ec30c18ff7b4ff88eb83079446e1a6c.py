# -*- coding: utf-8 -*-
"""
Created on Tue Dec  9 13:52:37 2014

@author: ashaver
"""
__author__ = 'ashaver'

def is_multiple(x, y):
    return ( (x%y) == 0 )

def is_leap_year(year): 
    if  ( is_multiple(year,4) and not is_multiple(year,100) ) \
        or is_multiple(year,400):
            return True
    return False
        
