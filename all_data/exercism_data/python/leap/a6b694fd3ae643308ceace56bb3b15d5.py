# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 20:00:24 2014
"""

def is_leap_year(year):
    if year%400==0:
        return True
    elif year%100==0:
        return False
    elif year%4==0:
        return True
