import datetime
import calendar

from datetime import date, timedelta
from calendar import monthrange, day_name

weekday_translation = dict(zip(day_name, range(7)))

def meetup_day(year, month, day_of_week, iteration):

    day_of_week = weekday_translation[day_of_week]
    (initial_weekday, days_in_month) = monthrange(year, month)
    days_in_month = calendar.monthrange(year, month)
    total_days = days_in_month[1]
    
    if iteration == 'last':
        adjusted = date(year, month, total_days)
        diff = (adjusted.weekday() - day_of_week) % 7
    
    return adjusted + timedelta(days = diff)
