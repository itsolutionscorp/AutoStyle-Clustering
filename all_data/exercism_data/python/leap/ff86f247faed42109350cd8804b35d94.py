# -*- coding: utf-8 -*-

# plain on every year that is evenly divisible by
# 4 except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400

def is_leap_year(year):
    return (not year % 4 and year % 100) or not year % 400
