# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)

def is_leap_year(year):
    # if year is divisible by 400, its definitely a leap year
    # else its only a leap year if its divisible by 4 and not divisible by 100
    return year % 400 == 0 or (year % 4 == 0 and year % 100 != 0)
