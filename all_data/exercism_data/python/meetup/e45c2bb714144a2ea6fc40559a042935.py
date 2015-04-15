from datetime import date


def meetup_day(year, month, dow, qualifier):
    first_day = date(year, month, 1)
    days_in_month = (date(year, month + 1, 1) - first_day).days
    if qualifier[0].isdigit():
        week_start = int(qualifier[0]) * 7 - 6
        meetup_date = day_in_week(first_day, dow, week_start)
    elif qualifier == "teenth":
        meetup_date = day_in_week(first_day, dow, 13)
    elif qualifier == "last":
        meetup_date = day_in_week(first_day, dow, days_in_month - 6)
    return meetup_date

def day_in_week(first_day, dow, start):
    for day in xrange(start, start + 7):
        new_date = first_day.replace(day=day)
        if new_date.strftime("%A") == dow:
            return new_date
