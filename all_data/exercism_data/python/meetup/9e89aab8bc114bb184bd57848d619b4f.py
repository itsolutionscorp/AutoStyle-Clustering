'''
meetup.py

Calculate the date of meetups.

Typically meetups happen on the same day of the week.

Examples are

- the first Monday
- the third Tuesday
- the Wednesteenth
- the last Thursday

There are exactly 7 days that end in '-teenth'. Therefore, one is
guaranteed that each day of the week will have a '-teenth' in every
month.
'''
from datetime import date, timedelta

def meetup_day(year, month, day_of_week, which):
    '''
    Given the year and month, find which day of the week matches the other params

    @param year: the year
    @param month: the month (integer)
    @param day_of_week: day of the week (string)
    @param which: one of '1st', '2nd', '3rd', '4th', 'last', 'teenth'

    @return: date object representing the given day
    '''
    day_in_week = {
            'Monday': 0,
            'Tuesday': 1,
            'Wednesday': 2,
            'Thursday': 3,
            'Friday': 4,
            'Saturday': 5,
            'Sunday': 6
        }

    d = date(year, month, 1)
    delta = timedelta(1)
    if which == 'last':
        d = date(year if month < 12 else year + 1,
                 month + 1 if month < 12 else 1,
                 1)
        delta = timedelta(-1)
        d += delta
    elif which == 'teenth':
        d = d.replace(day=13)
    else:
        d = d.replace(day=(1 + 7 * (int(which[0]) - 1)))

    weekday = day_in_week[day_of_week]

    while d.weekday() != weekday:
        d += delta

    return d
