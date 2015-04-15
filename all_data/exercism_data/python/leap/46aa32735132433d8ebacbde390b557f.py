# -*- coding: utf-8 -*-

def leap(year):
    return (year % 4 == 0 and not year % 100 == 0) or year % 400 == 0

