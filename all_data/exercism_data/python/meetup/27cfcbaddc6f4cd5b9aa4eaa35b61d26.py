#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Calculate the date of meetups.

Typically meetups happen on the same day of the week.

Examples are

- the first Monday
- the third Tuesday
- the Wednesteenth
- the last Thursday

There are exactly 7 days that end in '-teenth'. Therefore, one is
guaranteed that each day of the week will have a '-teenth' in every
month.
"""

from calendar import day_name as dn
from calendar import monthcalendar as mc
from datetime import date as dt

__version__ = '0.0.2'
__all__ = ['__version__', 'meetup_day']


D = [d for d in dn]
W = {
    'last':  -1,
    'first':  0, '1st': 0,
    'second': 1, '2nd': 1,
    'third':  2, '3rd': 2,
    'fourth': 3, '4th': 3,
    'teenth': lambda x: x in range(13, 20)
}

def meetup_day(y, m, d, n):
    """Returns the date of the meetup

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.2

    :param y: the year
    :param m: the month
    :param d: name of the day
    :param n: occurence of `d` in `m`
    """
    ds = filter(lambda x: x, [w[D.index(d)] for w in mc(y, m)])
    return dt(y, m, filter(W[n], ds)[0]) if callable(W[n]) else dt(y, m, ds[W[n]])
