from datetime import date
from calendar import weekday, monthrange

def meetup_day(year, month, day, dayQualifier):

    parseDays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

    if dayQualifier == 'teenth':

        if weekday(year, month, 13) == parseDays[day]:
            return date(year, month, 13)
        
        else:
            
            dayDiff = parseDays[day] - weekday(year, month, 13)
    
            if dayDiff < 0:
                dayDiff += 7

            return date(year, month, 13 + dayDiff)

    elif dayQualifier == 'last':
        
        if weekday(year, month, monthrange(year, month)[1]) == parseDays[day]:
            return date(year, month, monthrange(year, month)[1])
        
        else:

            dayDiff = weekday(year, month, monthrange(year, month)[1]) - parseDays[day]

            if dayDiff < 0:
                dayDiff += 7

            return date(year, month, monthrange(year, month)[1] - dayDiff) 

    else:
        
        if monthrange(year, month)[0] == parseDays[day]:
            actualDay = 1 + 7 * (int(dayQualifier[0]) - 1)
        
        else:

            dayDiff = parseDays[day] - monthrange(year, month)[0]

            if dayDiff < 0:
                dayDiff += 7
                
            actualDay = (1 + dayDiff) + 7 * (int(dayQualifier[0]) - 1)
    
        return date(year, month, actualDay)
