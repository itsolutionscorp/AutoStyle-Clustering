#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def is_leap_year(year):
    if year % 400 == 0 and year % 100 == 0:
        return True
    else:
        if year % 4 == 0:
            if year % 100 != 0:
                return True
            else:
                return False
        else:
            return False
