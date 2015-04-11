# -*- coding: utf-8 -*-
from itertools import chain
from datetime import date
from calendar import Calendar

cal = Calendar()

def day_of_week_number(day):
    """
    returns day week number for a day name
    """
    days_value = [x for x in cal.iterweekdays()]
    days_key = ['Monday', 'Tuesday', 'Wednesday',
                'Thursday', 'Friday', 'Saturday', 'Sunday']
    days = dict(zip(days_key, days_value))
    return days.get(day)


def meetup_day(year, month, day, day_desc):
    week_day = day_of_week_number(day)

    # monthday2calendar returns a list with a list of tuples per each week
    # each tuple has (day_of_month, day_of_week) format
    # for completion on weeks, monthdays2calendar inserts zeros
    month_days = cal.monthdays2calendar(year, month)

    # now we get a list of target days
    # chain helps to flatten list
    days = [x[0] for x in chain(*month_days)
            if x[1] == week_day and x[0] !=0]

    if day_desc == '1st':
        return date(year, month, days[0])
    elif day_desc == '2nd':
        return date(year, month, days[1])
    elif day_desc == '3rd':
        return date(year, month, days[2])
    elif day_desc == '4th':
        return date(year, month, days[3])
    elif day_desc == 'last':
        return date(year, month, days.pop())
    else:
        teenth_day = [x for x in days if x in range(13,20)]
        return date(year, month, teenth_day.pop())
