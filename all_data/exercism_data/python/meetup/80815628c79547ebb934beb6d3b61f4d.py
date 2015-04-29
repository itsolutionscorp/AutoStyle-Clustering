import calendar
from datetime import date, timedelta
KEY = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}


def meetup_day(year, month, weekday, which):
    wday = getattr(calendar, weekday.upper())
    factor = KEY.get(which, 0)
    if which == 'teenth':
        d = date(year, month, 13)
    elif which == 'last':
        _, ndays = calendar.monthrange(year, month)
        d = date(year, month, ndays)
    else:
        d = date(year, month, 1)
    tday = d.weekday()
    delta = wday - tday if tday <= wday else 7 - (tday - wday)
    newd = d + timedelta(days=delta)
    if not delta and factor < 1:
        return newd
    return newd + timedelta(days=7 * factor)
