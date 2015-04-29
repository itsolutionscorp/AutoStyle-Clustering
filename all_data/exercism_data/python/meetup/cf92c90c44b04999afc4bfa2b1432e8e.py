__author__ = 'Hinek'

import datetime
import calendar

WEEKDAYS = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
    'Friday': 4, 'Saturday': 5, 'Sunday': 6}
ORDINALS = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4}

def next_weekday(d, weekday):
    days_ahead = weekday - d.weekday()
    if days_ahead < 0:
        days_ahead += 7
    return d + datetime.timedelta(days_ahead)

def meetup_day(year, month, weekday_str, ordinal_str):
    first_day_of_month = datetime.date(year, month, 1)
    first_weekday = next_weekday(first_day_of_month, WEEKDAYS[weekday_str]).day
    last_day_of_month = calendar.monthrange(year, month)[1]
    weekday_numbers = [first_weekday]
    n = first_weekday + 7
    while n <= last_day_of_month:
        weekday_numbers.append(n)
        n += 7
    if ordinal_str == 'last':
        day = weekday_numbers[-1]
    elif ordinal_str == 'teenth':
        for d in weekday_numbers:
            if d > 12 and d < 20:
                day = d
                break
    else:
        day = weekday_numbers[ORDINALS[ordinal_str]]
    return datetime.date(year, month, day)
