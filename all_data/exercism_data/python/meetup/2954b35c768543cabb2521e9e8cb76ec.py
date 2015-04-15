#
# Returns the date of a meetup based on the day of the week. 
#

from datetime import date, datetime
from calendar import monthrange

def meetup_day(year, month, day_of_week, ordinal):
    day_of_week = day_of_week[:3]
    total_days = monthrange(year, month)[1]
    days = [datetime(year, month, i).strftime('%a') for i in range(1, total_days+1)]
    ords = {'1st':1, '2nd':2, '3rd':3, '4th':4,
            'last':days.count(day_of_week),
            'teenth':10}
    
    count = 0
    for i in range(total_days):
        if days[i] == day_of_week:
            count += 1
            if count == ords[ordinal]:
                day_of_month = i + 1
                break
            elif ordinal == 'teenth':
                if i >= 12:              # Index of 12 is day 13 of the month
                    day_of_month = i + 1
                    break
            

    return date(year, month, day_of_month)
