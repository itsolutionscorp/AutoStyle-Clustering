from calendar import monthrange
from datetime import date

def meetup_day(year, month, day_of_the_week, which):
    month_length = monthrange(year, month)[1]
    days_in_month = (date(year, month, day)
            for day in range(1, month_length + 1))
    
    candidates = [date_
            for daye_ in days_in_month
            if day_name(date_) == day_of_the_week]

    if which == 'teenth':
        return next(d for d in candidates if 13 <= d.day <= 19)
    if which == 'last':
        return candidates[-1]
    return candidates[int(which[0]) - 1 ]

def day_name(date_):

    return date_.strftime('%A')
