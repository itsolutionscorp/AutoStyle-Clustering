import calendar
from datetime import date
import re


DAYS_OF_THE_WEEK = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday',
                    'saturday', 'sunday']


def meetup_day(year, month, weekday_text, type):
    month_grid = calendar.monthcalendar(year, month)
    weekday = get_weekday_index(weekday_text)
    return date(year, month, get_day(month_grid, weekday, type))


def get_day(month_grid, weekday, type):
    ordinal = re.search('\d', type)

    if type == 'teenth':
        return get_teenth_day(month_grid, weekday)
    elif type == 'last':
        return get_last_day(month_grid, weekday)
    elif ordinal:
        return get_ordinal_day(month_grid, weekday, int(ordinal.group()))

    raise ValueError('Bad type given')


def get_weekday_index(weekday_text):
    try:
        return DAYS_OF_THE_WEEK.index(weekday_text.lower())
    except ValueError:
        raise ValueError('Bad weekday given')


def get_teenth_day(month_grid, weekday):
    for i, week in enumerate(month_grid):
        if 13 in week:
            start = (i, week.index(13))
    if weekday >= start[1]:
        return month_grid[start[0]][weekday]
    else:
        return month_grid[start[0] + 1][weekday]


def get_last_day(month_grid, weekday):
    if month_grid[-1][weekday]:
        return month_grid[-1][weekday]
    else:
        return month_grid[-2][weekday]


def get_ordinal_day(month_grid, weekday, n):
    if n > 5:
        raise ValueError('Bad ordinal given')

    if month_grid[0][weekday]:
        return month_grid[n - 1][weekday]
    else:
        return month_grid[n][weekday]
