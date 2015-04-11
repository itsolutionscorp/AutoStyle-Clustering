import calendar
from datetime import date

days_of_the_week = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday':6
    }


def meetup_day(year, month, dayStr, selectStr):
    
    
    days_in_month = calendar.monthrange(year, month)[1] + 1

    
    if selectStr == 'teenth':
        
        for i in range(13, 20):
            temp_date = date(year, month, i)
            
            if ( days_of_the_week[dayStr] == temp_date.weekday() ):
                return temp_date
            
    elif selectStr == 'last':
        
        for i in reversed(range(1, days_in_month)):
            temp_date = date(year, month, i)
            
            if ( days_of_the_week[dayStr] == temp_date.weekday() ):
                return temp_date
            
    else:
        num = int(selectStr[0])
        count = 0

        for i in range(1, days_in_month):

            temp_date = date(year, month, i)
            if ( days_of_the_week[dayStr] == temp_date.weekday() ):
                count += 1

            if count == num:
                return temp_date
