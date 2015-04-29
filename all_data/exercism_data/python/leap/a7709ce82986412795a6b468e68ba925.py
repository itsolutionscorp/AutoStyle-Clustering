#!/usr/bin/env python
# -*- coding: utf-8 -*-


def is_leap_year(year):
    return year % 4 == 0 and (year % 400 == 0 or year % 100 != 0)
