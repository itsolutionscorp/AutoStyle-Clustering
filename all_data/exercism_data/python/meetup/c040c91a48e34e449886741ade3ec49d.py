import datetime


def meetup_day(year, month, weekday, cond):
    weekdays = dict(zip(['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'], range(0, 7)))
    nth_day = dict(zip(['1st', '2nd', '3rd', '4th', 'last'], [0, 1, 2, 3, -1]))
    teenth_days = range(13,20)

    dt = datetime.date(year, month, 1)
    dt_lst = []

    while dt.weekday() != weekdays[weekday]:
        dt += datetime.timedelta(days=1)
    while dt.month == month:
        dt_lst.append(dt)
        dt += datetime.timedelta(days=7)

    if cond in nth_day:
        return dt_lst[nth_day[cond]]

    for d in dt_lst:
        if d.day in teenth_days:
            return d
