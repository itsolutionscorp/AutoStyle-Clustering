import calendar
from datetime import date

NTH = ('1st', '2nd', '3rd', '4th')


def meetup_day(year, month, week_day, enth):
    cal = calendar.monthcalendar(year, month)
    wd = getattr(calendar, week_day.upper())
    if enth == 'last':
        day = cal[-1][wd]
        if day == 0:
            day = cal[-2][wd]
    elif enth == 'teenth':
        for i in (1,2,3):
            day = cal[i][wd]
            if day > 12:
                break
    else:
        day1 = cal[0][wd]
        if day1 == 0:
            day1 = cal[1][wd]
        day = day1 + NTH.index(enth)*7
    return date(year, month, day)
