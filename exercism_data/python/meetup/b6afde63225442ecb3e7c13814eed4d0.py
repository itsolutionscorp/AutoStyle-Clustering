from datetime import date
from calendar import day_name, weekday, monthrange
week = dict(zip(day_name, range(7)))
addict = dict(zip(["1st", "2nd", "3rd", "4th", "teenth"], [0, 7, 14, 21, 0]))


def meetup_day(year, month, wday, cond):
    stday = 1
    if cond == "teenth":
        stday = 13
    elif cond == "last":
        stday = monthrange(year, month)[1]
    stwday = weekday(year, month, stday)
    if cond == "last":
        add = -(stwday - week[wday] % 7)
    else:
        add = (week[wday]-stwday) % 7 + addict[cond]
    return date(year, month, stday+add)
