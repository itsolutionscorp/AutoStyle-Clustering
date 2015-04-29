# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 13:41:55 2014

@author: rebuhr
"""

# program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary
# gigasecond is one billion (10**9) seconds

from datetime import date

def add_gigasecond(startdate):
    gigann = startdate.toordinal() + 11574 # 10**9 / 60 sec in 1 min / 60 min in 1 hr / 24 hr in 1 day
    return date.fromordinal(gigann)
