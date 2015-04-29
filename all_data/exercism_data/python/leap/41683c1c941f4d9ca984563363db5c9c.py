#!/usr/bin/env python
# encoding: utf-8

def is_leap_year(year):
    return not (year % 4 or not year % 100 and year % 400)
