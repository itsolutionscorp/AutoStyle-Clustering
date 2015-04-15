# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
from calendar import monthrange
import datetime

def meetup_day(year, month, dow, wom):
    """Returns the day of the meetup"""
    first_dow = monthrange(year, month)[0]
    days_in_month = monthrange(year, month)[1]
    possible_dates = []
    print str(year) + str(month) + dow + wom

    """Build dictionary of possible dates based on dow"""
    for day in range(1, days_in_month+1):
        if datetime.date(year, month, day).strftime("%A") == dow:
            print day
            possible_dates.extend([day])

    """Perform logic on wom constraint"""
    if wom == "teenth":
        for day in possible_dates:
            if day > 12 and day < 20:
                return datetime.date(year, month, day)
    elif wom == "last":
        return datetime.date(year, month, possible_dates[-1])
    else:
        return datetime.date(year, month, possible_dates[ int(wom[:1]) - 1 ])
