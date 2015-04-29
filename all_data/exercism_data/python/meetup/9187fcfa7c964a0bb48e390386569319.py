from datetime import date
import sys

week_days = ['Monday',
             'Tuesday',
             'Wednesday',
             'Thursday',
             'Friday',
             'Saturday',
             'Sunday']

week_day_to_n = {'Monday': 1 ,
                 'Tuesday' : 2  ,
                 'Wednesday' : 3,
                 'Thursday' : 4 ,
                 'Friday' : 5 ,
                 'Saturday' : 6 ,
                 'Sunday' : 7 }

def nth(which):
    ret = 0
    if which in 'last':
        ret = 3
    else:
        ret = (int(which[0]) - 1)

    return ret * 7

def meetup_day(year, month, week_day, which):

    if which in 'teenth':
        for day in range(10, 19):
            d = date(year, month, day)
            if d.weekday() + 1 == week_day_to_n[week_day]:
                return d

    first = date(year, month, 1).weekday() + 1
    first_weekday = (week_day_to_n[week_day] - first) % 7 + 1
    day = first_weekday + nth(which)
    return date(year, month, day)
