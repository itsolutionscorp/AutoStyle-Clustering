# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def is_leap_year(year):
    """Implements leap year logic"""
    return year % 400 == 0 or (year % 4 == 0 and not year % 100 == 0)
