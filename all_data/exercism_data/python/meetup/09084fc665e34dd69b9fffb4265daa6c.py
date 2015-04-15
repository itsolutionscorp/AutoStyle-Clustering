import datetime
import calendar

def get_teenth(days):
    for date in days:
        if 13 <= date <= 19:
            return date

def meetup_day(year, month, weekday, selector):
    weekday = getattr(calendar, weekday.upper())
    days_of_month = calendar.Calendar().itermonthdays2(year, month)
    candidates = [date for date,day in days_of_month if day == weekday and date]

    if selector == 'teenth':
        return datetime.date(year, month, get_teenth(candidates))

    index = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1,}[selector]
    return datetime.date(year, month, candidates[index])
