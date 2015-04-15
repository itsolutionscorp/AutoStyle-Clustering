from calendar import monthrange, weekday, day_name
from datetime import date


def meetup_day(year, month, day_str, eenth):

    dotw = [x for x in day_name]
    day_int = dotw.index(day_str)
    _, month_length = monthrange(year, month)
    every_day = []

    for day in range(1, month_length+1):
            if weekday(year, month, day) is day_int:
                every_day.append(day)
    
    if eenth is '1st':
        return date(year, month, every_day[0])
    if eenth is '2nd':
        return date(year, month, every_day[1])
    if eenth is '3rd':
        return date(year, month, every_day[2])
    if eenth is '4th':
        return date(year, month, every_day[3])
    if eenth is 'last':
        return date(year, month, every_day[-1])
    if eenth is 'teenth':
        teen = [x for x in every_day if 12 < x < 20]
        return date(year, month, teen[0])
