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


def _last(year, month, target_weekday):
    _, last_day = monthrange(year, month)
    dt = date(year, month, last_day)
    return dt + timedelta(days=-(dt.weekday() - target_weekday) % 7)


def _teenth(year, month, target_weekday):
    dt = date(year, month, 13)
    return dt + timedelta(days=((target_weekday - dt.weekday()) % 7))


def _nth_day(year, month, target_weekday, offset_weeks):
    offset_days = offset_weeks * 7
    dt = date(year, month, 1)
    return dt + timedelta(days=((target_weekday - dt.weekday()) % 7) + offset_days)


def meetup_day(year, month, weekday, offset):
    target_weekday = WEEKDAYS.index(weekday)
    if offset == 'last':
        return _last(year, month, target_weekday)
    elif offset == 'teenth':
        return _teenth(year, month, target_weekday)
    else:
        offset_weeks = int(re.match(r'\d+', offset).group()) - 1
        return _nth_day(year, month, target_weekday, offset_weeks)
