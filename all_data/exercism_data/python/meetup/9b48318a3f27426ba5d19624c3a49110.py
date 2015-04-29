from datetime import date, timedelta

def meetup_day(year, month, day, criteria):
    nthdaycriteria = ['1st', '2nd', '3rd', '4th']
    weekday = daytoweekday(day)
    if criteria in nthdaycriteria:
        return nthday(year, month, weekday, int(criteria[0]))
    elif criteria == 'teenth':
        return teenth(year, month, weekday)
    elif criteria == 'last':
        return lastday(year, month, weekday)
    else:
        raise ValueError('invalid criteria')

def daytoweekday(day):
    if day == 'Monday': return 0
    elif day == 'Tuesday': return 1
    elif day == 'Wednesday': return 2
    elif day == 'Thursday': return 3
    elif day == 'Friday': return 4
    elif day == 'Saturday': return 5
    elif day == 'Sunday': return 6
    else: raise ValueError('invalid day of week')

def nthday(year, month, weekday, n):
    dayscounted = 0
    d = date(year, month, 1)
    while dayscounted < n:
        if d.weekday() == weekday:
            dayscounted += 1
            if(n == dayscounted): return d
        d += timedelta(days=1)

def lastday(year, month, weekday):
    d = date(year, month + 1, 1)
    while True:
        d -= timedelta(days=1)
        if d.weekday() == weekday:
            return d

def teenth(year, month, weekday):
    d = date(year, month, 13)
    while d.weekday() != weekday:
        d += timedelta(days=1)
    return d
