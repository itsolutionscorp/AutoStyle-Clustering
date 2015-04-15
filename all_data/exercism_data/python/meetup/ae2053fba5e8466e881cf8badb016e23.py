__author__ = 'Dalton'
import calendar

def meetup_day(year, month, weekday, kind):

    day_of_week = dict(zip(list(calendar.day_name), range(7)))
    c = calendar.Calendar()

    days = [d for d in c.itermonthdates(year, month) if d.weekday() == day_of_week[weekday] and d.month == month]

    if kind == 'teenth':
        meetup = [d for d in days if d.day >= 13 and d.day <= 19][0]
    elif kind == 'last':
        meetup = days[-1]
    else:
        meetup = days[int(kind[0])-1]

    return meetup
