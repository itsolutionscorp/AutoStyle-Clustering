import datetime
import time

def weekday(name):
    return time.strptime(name, '%A').tm_wday

def meetup_day(year, month, weekday_name, which):
    if which == 'teenth':
        date = datetime.date(year, month, 10)
    else:
        date = datetime.date(year, month, 1)

    while date.weekday() != weekday(weekday_name):
        date += datetime.timedelta(days=1)

    if which == '2nd':
        date += datetime.timedelta(weeks=1)
    if which == '3rd':
        date += datetime.timedelta(weeks=2)
    if which == '4th':
        date += datetime.timedelta(weeks=3)
    if which == 'last':
        date += datetime.timedelta(weeks=4)

    return date
