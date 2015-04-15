#!/usr/bin/env python3

def is_leap_year(year):
    if year % 100:
        return not year % 4
    return not year % 400
