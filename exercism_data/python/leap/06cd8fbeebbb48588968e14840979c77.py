# -*- coding: utf-8 -*-
"""leap test"""


def is_leap_year(year):
    """check leap year"""
    return int(year) %4 == 0 and (int(year) % 400 == 0 or int(year) % 100 != 0)
