from calendar import monthrange, day_name
from datetime import date

weekdays = list(day_name)
weeks = dict(zip("last 1st 2nd 3rd 4th".split(),range(-1,4)))

def meetup_day(year, month, day, week):
    """Return the desired date based on the nth weekday of the month."""
    monthStart, days = monthrange(year,month)
    firstDay = (weekdays.index(day) - monthStart) % 7 + 1  #first weekday of month
    if week != 'teenth':
        return(date(year, month, range(firstDay, days+1, 7)[weeks[week]]))
    return(date(year, month,
                list((i for i in range(firstDay,days+1,7) if i > 12 and i < 20))[0]))
