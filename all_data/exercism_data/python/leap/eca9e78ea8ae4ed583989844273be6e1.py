#!/usr/bin/env python
# -*- coding: utf-8 -*-

def is_leap_year(year):
    if (year % 100 == 0) and (year % 400 != 0):
        return False
    elif (year % 4):
        return False
    else:
        return True
