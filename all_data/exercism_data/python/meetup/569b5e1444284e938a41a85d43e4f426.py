from datetime import date


def meetup_day(year, month, dow, qualifier):
    days_in_month = (date(year, month + 1, 1) - date(year, month, 1)).days
    char1 = qualifier[0]
    if char1.isdigit():
        week_start = int(char1) * 7 - 6
        for day in xrange(week_start, week_start + 7):
            if date(year, month, day).strftime("%A") == dow:
                return date(year, month, day)
    elif qualifier == "teenth":
        for day in xrange(13, 20):
            if date(year, month, day).strftime("%A") == dow:
                return date(year, month, day)
    elif qualifier == "last":
        for day in xrange(days_in_month - 6, days_in_month + 1):
            if date(year, month, day).strftime("%A") == dow:
                return date(year, month, day)
