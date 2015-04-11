# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)
from datetime import date, timedelta

days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
                'Saturday', 'Sunday']


def _proper_descriptor(descriptor):
    if descriptor == 'first':
        return '1st'
    if descriptor == 'second':
        return '2nd'
    if descriptor == 'third':
        return '3nd'
    if descriptor == 'fourth':
        return '4th'
    if descriptor == 'fifth':
        return '5th'
    return descriptor

def meetup_day(year, month, dow, descriptor):
    dow_idx = days_of_week.index(dow)
    last_of_month = date(year, month+1, 1) - timedelta(days=1)
    if descriptor == 'teenth':
        # use the 13th as a reference and count the dows up to `dow`
        idx_13 = date(year, month, 13).weekday()
        day = 13 + abs(idx_13 - dow_idx)
    elif descriptor == 'last':
        # use last day of the month as a reference for the dow and work
        # backwards to find the correct dow
        last_dow = last_of_month.weekday()
        day = last_of_month.day - abs(last_dow - dow_idx)
    else:
        descriptor = _proper_descriptor(descriptor)
        # The most a day can appear is 5 times
        weeks_ahead = 0
        try:
            i = int(descriptor[0])
            if i < 1 or i > 5:
                raise Exception("Descriptor number must be between 1 and 5")
            weeks_ahead = 7 * (i - 1)
        except ValueError:
            raise Exception("Invalid date descriptor")
        # use first of month as a reference point and calculate dow forwards
        first_of_month = date(year, month, 1)
        first_dow = first_of_month.weekday()
        day = (dow_idx - first_dow)
        # cater for case where day is negative
        if day < 0:
            day = 7 - abs(day)
        day = (1 + day) + weeks_ahead

        # for the case of the 5th, we ensure the day is within bounds
        if day > last_of_month.day:
            raise Exception("No {} {} of the month exists"
                            .format(descriptor, dow))
    return date(year, month, day)
