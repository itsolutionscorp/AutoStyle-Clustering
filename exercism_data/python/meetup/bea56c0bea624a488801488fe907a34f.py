import calendar

from datetime import date


def meetup_day(year, month, weekday, nth):
    days_of_week = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
    day = days_of_week.index(weekday)
    if nth == 'teenth':
        for e in xrange(13, 20):
            d = date(year, month, e)
            if d.weekday() == day:
                return d
    elif nth == 'last':
        for e in range(1, calendar.monthrange(year, month)[1] + 1)[::-1]:
            d = date(year, month, e)
            if d.weekday() == day:
                return d
    else:
        count = 0
        n = 1 if nth == '1st' else 2 if nth == '2nd' else 3 if nth == '3rd' else 4
        for e in xrange(1, calendar.monthrange(year, month)[1] + 1):
            d = date(year, month, e)
            if d.weekday() == day:
                count += 1
                if count == n:
                    return d
