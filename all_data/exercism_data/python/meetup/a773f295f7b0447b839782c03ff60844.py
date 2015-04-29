from calendar import day_name, monthrange, monthcalendar
from datetime import date


def meetup_day(year, month, day_of_week, meet):

    start, total = monthrange(year, month)

    day_n  = list(day_name).index(day_of_week)
    offset = (day_n + 1) // (start + 1)

    if meet[0].isdigit():
        week = int(meet[0]) - offset
    if meet == 'last':
        week = -1
    if meet == 'teenth':
        week = (3>start) + offset + 1

    day = monthcalendar(year, month)[week][day_n]

    return date(year, month, day)
