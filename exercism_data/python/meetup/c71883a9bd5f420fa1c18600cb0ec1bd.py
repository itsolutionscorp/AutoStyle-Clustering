from calendar import monthrange
from datetime import date

def meetup_day(year, month, dow, argument):
    lengthmonth = monthrange(year, month)[1]
    daysInMonth = (date(year, month, day) for day in
                    range(1, lengthmonth + 1))
    qualified = [d for d in daysInMonth
                if get_datename(d) == dow]

    if argument == 'teenth':
        return next(d for d in qualified if 13 <= d.day <= 19)
    if argument == 'last':
        return qualified[-1]
    return qualified[int(argument[0]) - 1]

def get_datename(d):
    return d.strftime('%A')
