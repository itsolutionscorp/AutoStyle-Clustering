import time
import calendar
from datetime import datetime, timedelta


def meetup_day(year, month, day_name, rule):
    if rule == 'teenth':
        return meetup_day_teenth(year, month, day_name)
    return meetup_day_weekly(year, month, day_name, rule)


def meetup_day_teenth(year, month, day_name):
    result = datetime(year, month, 13)
    while result.day < 20 and result.weekday() != to_weekday(day_name):
        result += timedelta(days=1)
    return result.date()


def meetup_day_weekly(year, month, day_name, rule):
    weeks = {"1st": 1, "2nd": 2, "3rd": 3, "4th": 4, "last": 5}
    result = first_weekday(year, month, day_name)
    result += timedelta(days=(weeks[rule] - 1) * 7)
    return result.date()


def first_weekday(year, month, day_name):
    result = datetime(year, month, 1)
    while result.weekday() != to_weekday(day_name):
        result += timedelta(days=1)
    return result


def to_weekday(day_name):
    return dict(zip(calendar.day_name, range(0, 7)))[day_name]
