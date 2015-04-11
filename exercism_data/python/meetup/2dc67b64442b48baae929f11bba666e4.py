from datetime import date
from calendar import monthcalendar, day_name

week_days = dict(zip(day_name, range(0, 7)))
n_weeks = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3}

def first_week_has_day(month, day):
    if month[0][day]:
        return True
    return False

def last_week_has_day(month, day):
    if month[-1][day]:
        return True
    return False

def nth_week(month, day, n):
    if first_week_has_day(month, day):
        return month[n][day]
    return month[n + 1][day]

def last_week(month, day):
    if last_week_has_day(month, day):
        return month[-1][day]
    return month[-2][day]

def is_teenth(day):
    return 12 < day and day < 20

def teenth_week(month, day):
    for week in month:
        if is_teenth(week[day]):
            return week[day]

def meetup_day(year, month, day, suffix):
    calendar_month = monthcalendar(year, month)
    if suffix == "teenth":
        day_date = teenth_week(calendar_month, week_days[day])
    elif suffix == "last":
        day_date = last_week(calendar_month, week_days[day])
    elif suffix in n_weeks:
        day_date = nth_week(calendar_month, week_days[day], n_weeks[suffix])
    return date(year, month, day_date)
