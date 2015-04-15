from datetime import date
from calendar import Calendar


def meetup_day(year, month, weekday, suffix):
    # compose list of date objects
    # only for the required days
    month_iter = Calendar().itermonthdates(year, month)
    days = [dt for dt in month_iter 
            if  dt.strftime('%A') == weekday.capitalize() and \
                dt.month == month]

    if suffix == 'teenth':
        return next(dt for dt in days if dt.day >= 13)
    
    try:
        # get desired day number from string
        idx = int(suffix[0])-1
    except ValueError:
        # if fail, we are looking for 'last'
        idx = -1

    return days[idx]
