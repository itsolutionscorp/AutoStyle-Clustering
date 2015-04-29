import calendar
import datetime
from dateutil.relativedelta import relativedelta

def meetup_day(year, month, weekday, qualifier):
    weekdays = dict(zip(list(calendar.day_name), range(7)))
    steps = dict(zip(['1st', '2nd', '3rd', '4th', 'last'], range(0, 31, 7)))
    steps.update({'teenth' : 13})

    date = datetime.date(year, month, 1)
    weekday = weekdays[weekday]
    initial_day = steps[qualifier]

    return date + relativedelta(day=initial_day, weekday=weekday)
