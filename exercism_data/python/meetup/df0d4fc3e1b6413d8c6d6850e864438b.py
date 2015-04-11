from datetime import date
from datetime import timedelta
import calendar

def meetup_day(year, month, day, ordinal):
    day_of_week = {'Sunday': 6, 'Monday': 0, 'Tuesday': 1, 'Wednesday': 2,
                    'Thursday': 3, 'Friday': 4, 'Saturday': 5}
    ordinal_map = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3}
    first_of_month = date(year, month, 1)
    
    days = day_of_week[day] - first_of_month.weekday()
    days += 7 * (days < 0)
    
    try:
        days += 7 * ordinal_map[ordinal]
    except KeyError:
        if ordinal == 'teenth':
            days += (7*x for x in range(5) if 7*x+days+1 in range(13,20)).next()
        if ordinal == 'last':
            print [7*x+days+1 for x in range(1,5)]
            days += (7*(x-1) for x in range(1,6)
                    if 7*x+days+1 > calendar.monthrange(year, month)[1]).next()
        
    return first_of_month + timedelta(days=days)
