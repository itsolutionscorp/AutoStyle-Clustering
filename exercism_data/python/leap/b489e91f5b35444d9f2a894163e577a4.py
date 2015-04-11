# -*-coding: utf-8-*-
#! /usr/bin/env python3

def is_leap_year(year):

    # Discard most of the cases
    if(year % 4 != 0):
        return False
    # year is divisible by 4
    else:
        # if year is divisible by 100 but not by 400 it is not a leap year
        if(year % 100 == 0 and year % 400 != 0):
            return False
        else:
            return True
