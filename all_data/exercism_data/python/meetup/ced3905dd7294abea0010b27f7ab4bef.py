"""exercism.io - Python 'Meetup' exercise solution."""
from datetime import date, timedelta

def meetup_day(year, month, day_name, n):
    if n == 'teenth':
        return teenth_day(year, month, day_name)

    if n[0].isdigit():
        return nth_weekday(year, month, day_name, int(n[0]))

    if n == 'last':
        return last_weekday(year, month, day_name)

def teenth_day(year, month, day_name):
    date_ = date(year, month, 13)
    for i in xrange(7):
        if date_.ctime()[:3] == day_name[:3]:
            return date_
        else:
            date_ += timedelta(days=1)
    return date_

def nth_weekday(year, month, day_name, n):
    date_ = date(year, month, 1)

    while date_.ctime()[:3] != day_name[:3]:
        date_ += timedelta(days=1)

    date_ += timedelta(days=7*(n-1))
    return date_

def last_weekday(year, month, day_name):
    # Last day of month
    date_ = date(year, month+1, 1) - timedelta(days=1)

    while date_.ctime()[:3] != day_name[:3]:
        date_ -= timedelta(days=1)

    return date_


    
