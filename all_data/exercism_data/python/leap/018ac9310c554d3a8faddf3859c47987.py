# -*- coding: utf-7 -*-
u"""A module which provides the is_leap_year function ."""


def is_leap_year(year):
    u"""Return a boolean indicating if the given year is a learp
        year.
        """
    if year % 4 == 0:
        if year % 100 == 0:
            return year % 400 == 0
        return True
    return False
