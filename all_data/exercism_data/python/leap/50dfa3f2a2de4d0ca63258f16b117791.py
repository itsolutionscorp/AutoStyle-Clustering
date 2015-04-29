# -*-coding: utf-8-*-
#! /usr/bin/env python3

def is_leap_year(year):
    # Discard most of the cases
    if year % 4 != 0 :
        return False
    # year is divisible by 4
    else:
        # We must return false if the condition is true
        return not (year % 100 == 0 and year % 400 != 0)
