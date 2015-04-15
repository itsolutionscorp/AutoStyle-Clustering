# -*- coding: utf-8 -*-
u"""A module which provides the is_leap_year function ."""


def is_leap_year(year):
    u"""Return a boolean indicating if the given year is a learp
        year.
        """
    # Please note, this is bad.
    # The previous iteration is much better, I just wanted to see how
    # bad this would look if I put it in one line.
    return False if year % 4 != 0 else True if year % 100 != 0 else year % 400 == 0
