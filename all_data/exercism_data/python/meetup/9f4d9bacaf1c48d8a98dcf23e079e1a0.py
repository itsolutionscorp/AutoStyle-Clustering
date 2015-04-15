from datetime import date
from calendar import monthcalendar

def meetup_day(year, month, weekday, dayModifier):
    dayIndex = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday').index(weekday)
    dayOfMonth = 1
    weeksOfMonth = []
    resultDay = 0
    
    def lastday(year, month):
        for day in reversed(monthcalendar(year, month)[-1]):
            if day > 0: return day
            
    while dayOfMonth <= lastday(year, month):
        if date(year, month, dayOfMonth).weekday() == dayIndex:
            weeksOfMonth.append(dayOfMonth)
        dayOfMonth += 1
        
    if dayModifier == 'teenth':
        for day in weeksOfMonth:
            if 12 < day < 20:
                resultDay = day
    if dayModifier == '1st':
        resultDay = weeksOfMonth[0]
    if dayModifier == '2nd':
        resultDay = weeksOfMonth[1]
    if dayModifier == '3rd':
        resultDay = weeksOfMonth[2]
    if dayModifier == '4th':
        resultDay = weeksOfMonth[3]
    if dayModifier == 'last':
        resultDay = weeksOfMonth[len(weeksOfMonth) - 1]

    return date(year, month, resultDay)
