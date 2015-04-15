# -*- coding: utf-8 -*-

def is_leap_year(num):
    """ test if year is leap """
    if num % 4 == 0 and (num % 100 != 0 or num % 400 == 0):
        return True
    else:
        return False
