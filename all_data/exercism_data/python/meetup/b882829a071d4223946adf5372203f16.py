from datetime import date
from calendar import monthrange

def meetup_day(year, month, dayweek, which):
    month_length = monthrange(year, month)[1]
    days_in_the_month = (date(year, month, day)
                         for day in range(1, month_length + 1))
    candidates = [date_
                  for date_ in days_in_the_month
                  if date_.strftime('%A') == dayweek]

    if which == 'teenth':
        return next(d for d in candidates if 13 <= d.day <= 19)
    if which == 'last':
        return candidates[-1]
    return candidates[int(which[0]) - 1]
