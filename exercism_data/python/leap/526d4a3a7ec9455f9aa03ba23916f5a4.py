#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Utility methods for 'year'
"""

from calendar import isleap

__version__ = '0.0.3'
__all__ = ['__version__', 'is_leap_year']


def is_leap_year(year):
    """Test if the supplied year is a leap year

    The test for a leap year is determined by
    the following rules:

    every year that is evenly divisible by 4
     except every year that is evenly divisible by 100
      unless that year is also evenly divisible by 400

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.3

    :param year: the year to test
    """
    return isleap(year)
