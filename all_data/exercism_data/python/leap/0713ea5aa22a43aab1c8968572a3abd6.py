#!/usr/bin/env python
# -*- coding: utf-8 -*-

from math import fmod
import unittest

def is_leap_year(year):
    """ Test for a leap year.
        """
    # Edge cases first.
    if fmod(year, 100) == 0:
        if fmod(year, 400) == 0:
            return True
        return False

    # Non-edge cases
    if fmod(year, 4) == 0:
        return True
    return False
