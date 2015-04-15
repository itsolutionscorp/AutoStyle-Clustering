# -*- coding: utf-8 -*-

def is_leap_year(year):
    out = False
    if year%4==0:
        out = True
        if year%100==0:
            out = False
            if year%400==0:
                out = True
    return out
