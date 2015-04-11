# -*- coding: utf-8 -*-
"""
Created on Tue Dec  9 13:52:37 2014

@author: ashaver
"""
def is_multiple(x, y):
    return ( (x%y) == 0 )

def is_leap_year(year): 
    return ( is_multiple(year,4) and \
    ( is_multiple(year,400) or \
    not is_multiple(year,100) ) )
