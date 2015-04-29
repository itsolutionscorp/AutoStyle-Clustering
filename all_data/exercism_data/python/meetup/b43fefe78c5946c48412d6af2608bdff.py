from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, whence):
    days = ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday')

    (first, total) = monthrange(year, month)
    day = (days.index(weekday) - first) % 7 + 1

    if whence == '2nd':
        day += 7
    elif whence == '3rd':
        day += 14
    elif whence == '4th':
        day += 21
    elif whence == 'teenth':
        if day < 6:
            day += 14
        else:
            day += 7
    elif whence == 'last':
        day += (total - day) / 7 * 7

    return date(year, month, day)
