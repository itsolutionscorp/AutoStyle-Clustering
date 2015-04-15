from calendar import monthrange
from datetime import date, timedelta
import re

WEEKDAYS = """
Monday
Tuesday
Wednesday
Thursday
Friday
Saturday
Sunday
""".strip().split()


def _calculate(calculate_from, target_weekday, offset_days=0):
    weekday_diff = target_weekday - calculate_from.weekday()
    return calculate_from + timedelta(days=(weekday_diff % 7) + offset_days)


def _last(year, month, target_weekday):
    _, last_day = monthrange(year, month)
    calculate_from = date(year, month, last_day)
    return calculate_from, target_weekday


def _teenth(year, month, target_weekday):
    calculate_from = date(year, month, 13)
    return calculate_from, target_weekday


def _nth_day(year, month, target_weekday, offset_weeks):
    offset_days = offset_weeks * 7
    calculate_from = date(year, month, 1)
    return calculate_from, target_weekday, offset_days


def meetup_day(year, month, weekday, offset):
    target_weekday = WEEKDAYS.index(weekday)
    if offset == 'last':
        inputs = _last(year, month, target_weekday)
    elif offset == 'teenth':
        inputs = _teenth(year, month, target_weekday)
    else:
        offset_weeks = int(re.match(r'\d+', offset).group()) - 1
        inputs = _nth_day(year, month, target_weekday, offset_weeks)
    return _calculate(*inputs)
