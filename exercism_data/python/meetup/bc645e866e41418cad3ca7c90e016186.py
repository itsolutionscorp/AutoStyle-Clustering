import calendar
from calendar import Calendar

WEEKDAYS = [name for name in calendar.day_name]
WEEK_ORD = {"1st" : 0,
            "2nd" : 1,
            "3rd" : 2,
            "4th" : 3,
            "last" : -1}

def find_teenth(days):
    return next(d for d in days if 12 < d.day < 20)

def meetup_day(year, month, weekday_name, which):
    cal = Calendar()
    weekday = WEEKDAYS.index(weekday_name)
    days = [d for d in cal.itermonthdates(year, month) 
                if d.month == month and d.weekday() == weekday]
    if (which == "teenth"):
        return find_teenth(days)
    else:
        return days[WEEK_ORD[which]]
