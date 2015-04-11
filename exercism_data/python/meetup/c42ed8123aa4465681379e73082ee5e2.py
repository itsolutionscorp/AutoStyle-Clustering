from datetime import date
from calendar import monthrange

WEEK = 7
LASTTEEN = 19

def meetup_day(year, month, weekday, whence):
    weeks = ('1st', '2nd', '3rd', '4th')
    days = ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday')

    # Determine the day of the month for the first 'weekday'.
    (first, total) = monthrange(year, month)
    day = (days.index(weekday) - first) % WEEK + 1

    if whence in weeks:
        day += weeks.index(whence) * WEEK
    elif whence == 'last':
        day += int((total - day) / WEEK) * WEEK
    elif whence == 'teenth':
        day += int((LASTTEEN - day) / WEEK) * WEEK

    return date(year, month, day)
