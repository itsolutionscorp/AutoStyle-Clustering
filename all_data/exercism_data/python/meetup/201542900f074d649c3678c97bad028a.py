import calendar
from datetime import date

def get_weekdays(year,month, weekday):
    month_calendar = calendar.monthcalendar(year, month)
    return  [row[weekday] for row in month_calendar if row[weekday] != 0]


def meetup_day(year, month, weekday_string, order):
    weekday = calendar.day_name[:].index(weekday_string)
    weekdays_in_month = get_weekdays(year, month, weekday)
    meetup_days = {'1st': weekdays_in_month[0],
                   '2nd': weekdays_in_month[1],
                   '3rd': weekdays_in_month[2],
                   '4th': weekdays_in_month[3],
                   'last': weekdays_in_month[-1],
                   'teenth': [day for day in weekdays_in_month if day >12 and day < 20].pop()}
    return date(year, month, meetup_days[order])
