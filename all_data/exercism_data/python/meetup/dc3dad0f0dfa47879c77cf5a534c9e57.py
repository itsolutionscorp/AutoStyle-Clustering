from datetime import date
from calendar import Calendar

conv = {
    '1st' : 0,
    '2nd' : 1,
    '3rd' : 2,
    '4th' : 3,
    'last' : -1
}   

def meetup_day(year, month, weekday, which_weekday):
    month_days = [day.day for day in Calendar().itermonthdates(year, month) if day.strftime('%A') == weekday and day.month == month]
    if which_weekday == 'teenth':
        for month_day in month_days:
            if month_day in range(13,20):
                day = month_day
    else: day = month_days[conv[which_weekday]]
    return date(year, month, day)
