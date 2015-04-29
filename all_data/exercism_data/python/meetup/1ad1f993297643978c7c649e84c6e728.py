from calendar import monthrange
from datetime import date

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
weeks = {'1st':0, '2nd':1, '3rd':2, '4th':3, 'last':-1}

def meetup_day(year, month, day, week):
    """Return the desired date based on the nth weekday of the specified month.""" 
    monthStart, days = monthrange(year,month)
    firstDay = (weekdays.index(day) - monthStart) % 7 + 1  #first desired weekday of month
    if week != 'teenth':
        return(date(year, month, range(firstDay, days+1, 7)[weeks[week]]))
    return(date(year, month, list((i for i in range(firstDay,days+1,7)\
                                   if i > 12 and i < 20))[0]))
