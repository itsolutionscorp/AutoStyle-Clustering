# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 14:47:41 2014
"""
from datetime import date
import calendar

dow_set = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday' : 3,
    'Friday' : 4,
    'Saturday' : 5,
    'Sunday' : 6
}

modifier_set = {
    '1st': 0,
    '2nd': 1,
    '3rd': 2,
    '4th': 3
}

teenth_list = [13, 14, 15, 16, 17, 18, 19]

def meetup_day(year, mth, dow, modifier):
    cal = calendar.monthcalendar(year, mth)
    fwd_num = calendar.weekday(year, mth, 1)
    dow_num = dow_set[dow]
    if modifier == 'last':
        return_day = cal[-1][dow_num]
        if return_day == 0:
            return_day = cal[-2][dow_num]
    elif modifier == 'teenth':
        return_day = 15 + dow_num - fwd_num
    else:
        cal_week_mod = modifier_set[modifier]
        if fwd_num > dow_num:
            cal_week_mod += 1

        return_day = cal[cal_week_mod][dow_num]

    return date(year, mth, return_day)


