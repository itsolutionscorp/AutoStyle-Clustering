from datetime import date
from calendar import monthrange


def meetup_day(year, month, week_day, type):

    n = -1
    if type == 'teenth':
        n = 13
    elif type == 'last':
        n = monthrange(year, month)[1] - 6
    else:
        n = 1 + 7 * ['1st', '2nd', '3rd', '4th'].index(type)

    return week_day_mday(year, month, week_day, n)


def week_day_mday(year, month, week_day, mday):

    for day in range(mday, mday + 7):
        dt = date(year, month, day)
        if (dt.strftime("%A") == week_day):
            return dt

    raise ValueError("No such day!")
