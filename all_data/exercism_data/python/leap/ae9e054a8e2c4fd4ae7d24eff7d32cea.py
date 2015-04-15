# -*- coding: utf-8 -*-
"""
Created on Tue Jan  6 23:30:41 2015

@author: fabian
"""

def is_leap_year(aYear):
    
    if float((aYear % 100) == 0):
        if float((aYear % 400) == 0):
            return True
    elif float((aYear % 4) == 0):
        return True
    
    return False
