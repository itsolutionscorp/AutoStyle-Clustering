#!/usr/bin/python
def is_leap_year(year):
    if year%4 != 0:
        return False
    if year%100 != 0:
        return True
    if year%400 != 0:
        return False
    else:
        return True