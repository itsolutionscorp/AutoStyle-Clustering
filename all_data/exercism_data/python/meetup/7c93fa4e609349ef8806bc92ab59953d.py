import calendar
from datetime import date


def meetup_day(year, month, day, week):
    day_int = list(calendar.day_name).index(day)
    cal = calendar.Calendar()
    cal_iterator = cal.itermonthdays2(year, month)

    if week[0].isnumeric():
        week_index = int(week[0]) - 1
    elif week == 'last':
        week_index = -1

    if week == 'teenth':
        date_int = next(filter(lambda x: 12 < x[0] < 20 and x[1] == day_int, cal_iterator))[0]
    else:
        date_int = [day for day in cal_iterator if day[1] == day_int and day[0] != 0][week_index][0]

    return date(year, month, date_int)
