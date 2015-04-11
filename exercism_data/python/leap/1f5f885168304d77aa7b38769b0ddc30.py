#!/usr/bin/env python3
"""
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
"""


def is_leap_year(i):
    if i % 4 != 0:
        return False
    if i % 100 == 0:
        return i % 400 == 0
    return True
