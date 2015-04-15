from calendar import monthcalendar
from re import search
from datetime import date

from_weekday = dict(zip(['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'], range(0, 7)))


def meetup_day(year, month, dayOfWeek, ordinal):
    calendar_month = monthcalendar(year, month)
    weekday = from_weekday[dayOfWeek]

    index = search(r'(\d)', ordinal)
    week_index = None
    week = None
    if index:
        week_index = int(index.group(0))
        if not calendar_month[0][weekday] == 0:
            week_index -= 1

        week = calendar_month[week_index]

    if not week_index:
        if ordinal == 'last':
            week = calendar_month[-1]
        elif ordinal == 'teenth':
            for w in calendar_month:
                if 12 < w[weekday] < 20:
                    week = w

    day = week[weekday]
    return date(year, month, day)
