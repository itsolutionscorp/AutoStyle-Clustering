from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, whence):
    days = ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday')

    weeks = ('1st', '2nd', '3rd', '4th')

    (first, total) = monthrange(year, month)
    day = (days.index(weekday) - first) % 7 + 1

    if whence in weeks:
        day += weeks.index(whence) * 7
    elif whence == 'last':
        day += (total - day) / 7 * 7
    elif whence == 'teenth':
        day += (19 - day) / 7 * 7

    return date(year, month, day)
