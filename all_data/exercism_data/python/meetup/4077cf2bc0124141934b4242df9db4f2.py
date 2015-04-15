import calendar as cal
import datetime as dt

def meetup_day(year, month, day_of_week, ordinal):
    days = []
    if ordinal == "teenth":
        days = range(13, 20)
    elif ordinal == "last":
        days = reversed(range(1, cal.monthrange(year, month)[1]+1))
    else:
        ord_num = ord(ordinal[0]) - ord('0') - 1
        days = range(7*ord_num + 1, 7*ord_num + 8)

    for day in days:
        if dt.date(year, month, day).strftime("%A") == day_of_week:
            return dt.date(year, month, day)
