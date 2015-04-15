# -*- coding: utf-8 -*-
"""leap test"""


def is_leap_year(year):
    """check leap year"""
    if int(year) % 400 == 0:
        return True
    elif int(year) % 100 == 0:
        return False
    elif int(year) % 4 == 0:
        return True
    else:
        return False
