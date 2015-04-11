from datetime import date
import calendar

week_days = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4, "Saturday": 5, "Sunday": 6}
n_weeks = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3}

def nth_week(month, day, n):
    if month[0][day]:
        return month[n][day]
    return month[n + 1][day]

def last_week(month, day):
    if month[-1][day]:
        return month[-1][day]
    return month[-2][day]

def is_teenth(day):
    return 12 < day and day < 20

def teenth_week(month, day):
    for week in month:
        if is_teenth(week[day]):
            return week[day]

def meetup_day(year, month, day, suffix):
    monthly_calendar = calendar.monthcalendar(year, month)
    if suffix == "teenth":
        day_date = teenth_week(monthly_calendar, week_days[day])
    elif suffix == "last":
        day_date = last_week(monthly_calendar, week_days[day])
    elif suffix in n_weeks:
        day_date = nth_week(monthly_calendar, week_days[day], n_weeks[suffix])
    return date(year, month, day_date)
