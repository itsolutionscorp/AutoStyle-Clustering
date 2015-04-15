from datetime import date, timedelta


class MeetupDayException(Exception):
    pass

day_names = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
day_specifiers = ['1st', '2nd', '3rd', '4th', '5th']

def get_month_days(year, month, day_name):
    days = []
    weekday = day_names.index(day_name)

    d = date(year, month, 1)
    while d.month == month:
        if d.weekday() == weekday:
            days.append(d.day)
            d += timedelta(days=7)
        else:
            d += timedelta(days=1)

    return days;

def meetup_day(year, month, day_name, day_specifier):
    days = get_month_days(year, month, day_name)

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
