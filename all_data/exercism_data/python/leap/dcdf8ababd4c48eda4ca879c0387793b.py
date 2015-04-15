# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 11:44:18 2014
"""


def is_leap_year(input_year):

    if input_year % 400 == 0:
        return_value = True
    elif input_year % 100 == 0:
        return_value = False
    elif input_year % 4 == 0:
        return_value = True
    else:
        return_value = False

    return return_value
