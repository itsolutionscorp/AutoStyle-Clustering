#!/usr/bin/env python3

def is_leap_year(int_year):
    assert(type(int_year) is int)
    # non divisible by 4 are not leap years
    if int_year % 4 != 0: return False:
    # exceptional centuries
    if (int_year % 100 == 0) & (int_year % 400 != 0):
        return False
    else:
        return True
