import calendar
from datetime import date


class MeetupDayException(Exception):
    pass

day_specifiers = ['1st', '2nd', '3rd', '4th', '5th']

def meetup_day(year, month, day_name, day_specifier):
    weekday = list(calendar.day_name).index(day_name)
    days = [d.day for d in calendar.Calendar().itermonthdates(year, month)
            if d.month == month and d.weekday() == weekday];

    if day_specifier in day_specifiers:
        try:
            idx = day_specifiers.index(day_specifier)
            return date(year, month, days[idx])
        except:
            raise MeetupDayException()
    elif day_specifier == 'last':
        return date(year, month, days[-1])
    elif day_specifier == 'teenth':
        day = [d for d in days if d > 12 and d < 20][0]
        return date(year, month, day)
