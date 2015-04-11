from datetime import date, timedelta

DAY_TO_WEEKDAY = dict(
    Monday=0,
    Tuesday=1,
    Wednesday=2,
    Thursday=3,
    Friday=4,
    Saturday=5,
    Sunday=6
)

def meetup_day(year, month, day, criteria):
    nthdaycriteria = ['1st', '2nd', '3rd', '4th']
    weekday = DAY_TO_WEEKDAY[day]
    
    if criteria in nthdaycriteria:
        return nthday(year, month, weekday, int(criteria[0]))
    elif criteria == 'teenth':
        return teenth(year, month, weekday)
    elif criteria == 'last':
        return lastday(year, month, weekday)
    else:
        raise ValueError('invalid criteria')

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
