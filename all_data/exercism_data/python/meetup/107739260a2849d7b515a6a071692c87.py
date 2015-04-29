from calendar import monthrange
from datetime import datetime, timedelta

WEEKDAYS = {
    'Monday'    : 0,
    'Tuesday'   : 1,
    'Wednesday' : 2,
    'Thursday'  : 3,
    'Friday'    : 4,
    'Saturday'  : 5,
    'Sunday'    : 6,
}

def meetup_day(year, month, day, day_of_month):
    weekday = WEEKDAYS[day]
    
    num_days = monthrange(year, month)[1]
    first_day_of_month = datetime(year, month, 1)
    month_dates = [first_day_of_month + timedelta(days=x) for x in range(0, num_days)]    

    days = [ date.date() for date in month_dates if date.weekday() == weekday ]
    
    if day_of_month == 'last':
        return days[-1]
    elif day_of_month == 'teenth':
        teenth_days = [d for d in days if d.day in range(13,20)]
        return teenth_days[0]
    else:
        return days[int(day_of_month[0]) - 1]
