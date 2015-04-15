import calendar
from datetime import date

WEEK_ORD = {"1st" : 1,
            "2nd" : 8,
            "3rd" : 15,
            "4th" : 22,
            "teenth" : 13}

def dates_generator(year, month, start_day, days):
    for day in range(start_day, start_day + days):
        yield date(year, month, day)
    

def meetup_day(year, month, weekday_name, which):
    if which == "last":
        start_day = calendar.monthrange(year, month)[1] - 6
    elif which in WEEK_ORD:
        start_day = WEEK_ORD[which]
    else:
        raise ValueError("Invalid meetup day")
    
    weekday = list(calendar.day_name).index(weekday_name)
    
    return next(day for day in dates_generator(year, month, start_day, 7)
                    if day.weekday() == weekday)
