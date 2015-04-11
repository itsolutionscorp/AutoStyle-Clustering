# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)
from datetime import date, timedelta

days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
               'Saturday', 'Sunday']

def meetup_day(year, month, dow, descriptor):
    dow_idx = days_of_week.index(dow)
    if descriptor == 'teenth':
        # use the 13th as a reference and count the dows up to `dow`
        idx_13 = date(year, month, 13).weekday()
        day = 13 + abs(idx_13 - dow_idx)
    elif descriptor == 'last':
        # use last day of the month as a reference for the dow and work
        # backwards to find the correct dow
        last_of_month = date(year, month+1, 1) - timedelta(days=1)
        last_dow = last_of_month.weekday()
        day = last_of_month.day - abs(last_dow - dow_idx)
    else:
        # use first of month as a reference point and calculate dow forwards
        first_of_month = date(year, month, 1)
        first_dow = first_of_month.weekday()
        day = (dow_idx - first_dow)
        # cater for case where day is negative
        if day < 0:
            day = 7 - abs(day)
        weeks_ahead = 7 * (int(descriptor[0]) - 1)
        day = (1 + day) + weeks_ahead
    return date(year, month, day)
