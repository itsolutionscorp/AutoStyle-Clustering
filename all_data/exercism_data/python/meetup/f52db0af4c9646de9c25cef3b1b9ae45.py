from calendar import monthcalendar
from re import search
from datetime import date

from_weekday = dict(zip(['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'], range(0, 7)))


def meetup_day(year, month, day, on_the):
    cal = monthcalendar(year, month)
    weekday = from_weekday[day]

    if on_the == 'last':
        d = cal[-1][weekday]
        return date(year, month, d)

    if on_the == 'teenth':
        d = [week[weekday] for week in cal if 12 < week[weekday] < 20][0]
        return date(year, month, d)

    n = search(r'(\d)', on_the)
    if n:
        wx = int(n.group(0))
        d = [week[weekday] for week in cal if week[weekday] != 0][wx - 1]
        return date(year, month, d)

    return None
