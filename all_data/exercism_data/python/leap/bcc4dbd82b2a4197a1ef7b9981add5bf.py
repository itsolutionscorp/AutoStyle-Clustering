#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Takes a year and reports whether it's a leap year.
"""


def is_leap_year(year):
    """Takes a year and reports whether it's a leap year."""

    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False
