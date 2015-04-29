from datetime import timedelta
from datetime import date
from calendar import monthrange

oneday = timedelta(days=1)
selectorStart = {'1st':1, '2nd':8, '3rd':15, '4th':22, 'teenth':13} # the first possible day of the month for any given selector
dayIndex = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}


def meetup_day(year, month, weekday, selector):
    if selector == 'last':
        day = date(year,month, monthrange(year, month)[1])
        while day.weekday() != dayIndex[weekday]:
            day -= oneday
        return day
        
    else:
        day = date(year, month, selectorStart[selector])
        while day.weekday() != dayIndex[weekday]:
            day += oneday
        return day
    
    
