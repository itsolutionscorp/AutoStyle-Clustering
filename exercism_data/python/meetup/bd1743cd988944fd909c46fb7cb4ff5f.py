from datetime import date
import calendar
import re

def meetup_day(year, month, day, position):
    lookup = __days_and_dows_in_month(year, month)
    if position == 'teenth':
        num = next(d for d, dow in lookup if day == dow and d in range(13, 19))
    elif position == 'last':
        num = next(d for d, dow in reversed(lookup) if day == dow)
    else:
        index = int(re.match(r'\d', position).group()) - 1
        num = [d for d, dow in lookup if day == dow][index]
    return date(year, month, num)

def __days_and_dows_in_month(year, month):
    all_days = __days_in_month(year, month)
    return [__pair_day_with_dow(year, month, day) for day in all_days]

def __days_in_month(year, month):
    length = calendar.monthrange(year, month)[1]
    return range(1, length + 1)

def __pair_day_with_dow(year, month, day):
    dow = date(year, month, day).strftime('%A')
    return (day, dow)
