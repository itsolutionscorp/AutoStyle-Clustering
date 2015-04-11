#!/usr/bin/python
def is_leap_year(year):
    four = year % 4 == 0
    one_hundred = year % 100 == 0
    four_hundred = year % 400 == 0
    if four:
        if four_hundred:
            return True
        if one_hundred:
            return False
        return True
    return False
