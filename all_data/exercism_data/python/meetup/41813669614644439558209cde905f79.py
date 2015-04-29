import datetime
import calendar


def get_teenth(days):
    for date in days:
        if date in [13, 14, 15, 16, 17, 18, 19]:
            return date

def get_weekday(days, weekday):
    weekday = getattr(calendar, weekday.upper())
    for date, day in days:
        if day == weekday and date:
            yield date

def meetup_day(year, month, weekday, selector):
    days_of_month = calendar.Calendar().itermonthdays2(year, month)
    potential_days = get_weekday(days_of_month, weekday)
    if selector == 'teenth':
        return datetime.date(year, month, get_teenth(potential_days))
    selectors = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1,}
    day = list(potential_days)[selectors[selector]]
    return datetime.date(year, month, day)
