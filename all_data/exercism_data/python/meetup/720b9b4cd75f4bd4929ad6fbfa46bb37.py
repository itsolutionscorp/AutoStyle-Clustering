#!/usr/bin/env python3
""" module meetup for exercism.io programming training"""

from datetime import date, timedelta

weekday_list = ['mo', 'tu', 'we', 'th', 'fr', 'sa', 'su']

def meetup_day(year=2000, month=2, weekday='Monday', target='last'):
    """ function returns suggested meetup date"""
    # find_dates 
    day_no = weekday_list.index(weekday[:2].lower())
    dates=[]
    for new_day in range(1, 32):
        try:
            if date(year, month, new_day).weekday() == day_no:
                dates.append(date(year, month, new_day))
        except:
            pass
    if target[0].isnumeric() and int(target[0]) < 5: 
        return dates[(int(target[0]) - 1)]
    if target == 'last':
        return dates[-1]
    if target == 'teenth':
        for i in dates:
            if i.day > 12: return i
    return dates
