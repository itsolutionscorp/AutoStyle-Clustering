from datetime import date


def meetup_day(year, month, dow, qualifier):
    days_in_month = (date(year, month + 1, 1) - date(year, month, 1)).days
    char1 = qualifier[0]
    if char1.isdigit():
        week_start = int(char1) * 7 - 6
        meetup_date = day_in_week(year, month, dow, week_start)
    elif qualifier == "teenth":
        meetup_date = day_in_week(year, month, dow, 13)
    elif qualifier == "last":
        meetup_date = day_in_week(year, month, dow, days_in_month -6)
    return meetup_date

def day_in_week(year, month, dow, start):
    for day in xrange(start, start + 7):
        if date(year, month, day).strftime("%A") == dow:
            return date(year, month, day)
