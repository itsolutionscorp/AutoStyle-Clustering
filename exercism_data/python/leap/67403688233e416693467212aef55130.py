#!/usr/bin/env python
from math import *

def is_leap_year(year):
    if type(year) is not int:
        print "Value provided is not an int."
        raise ValueError
    
    if not year % 4:
        if (not(year % 100) and (year % 400)) :
            return False
        return True
    return False
