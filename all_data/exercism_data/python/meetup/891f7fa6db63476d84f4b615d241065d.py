import time
import calendar
from datetime import datetime, timedelta


def meetup_day(year, month, dayOfWeek, rule):
    if rule == 'teenth':
        return meetup_day_teenth(year, month, dayOfWeek)

    return meetup_day_weekly(year, month, dayOfWeek, rule)


def meetup_day_teenth(year, month, day_name):
    result = datetime(year, month, 13)
    while result.day < 20 and result.weekday() != days_of_week()[day_name]:
        result += timedelta(days=1)
    return result.date()


def meetup_day_weekly(year, month, day_name, rule):
    weeks = {"1st": 1, "2nd": 2, "3rd": 3, "4th": 4, "last": 5}

    result = datetime(year, month, 1)
    while result.weekday() != days_of_week()[day_name]:
        result += timedelta(days=1)

    result += timedelta(days=(weeks[rule] - 1) * 7)

    return result.date()


def days_of_week():
    return {name: index for index, name in enumerate(calendar.day_name)}
