from datetime import date, timedelta
from calendar import monthrange

# Weekdays constant
DAYS = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

def meetup_day(yr, mth, wkday, qualifier):

    delta = 0
    
    # find the first instance of the weekday in the month
    delta = DAYS[wkday] - monthrange(yr, mth)[0]
    if delta < 0:
        delta += 7
    
    if qualifier == '1st':
        pass
    elif qualifier == '2nd':
        delta += 7
    elif qualifier == '3rd':
        delta += 14
    elif qualifier == '4th':
        delta += 21
    elif qualifier == 'last':
        delta = DAYS[wkday] - date(yr, mth, monthrange(yr, mth)[1]).weekday()
        if delta > 0:
            delta - 7
        delta += monthrange(yr, mth)[1] - 1
    elif qualifier == 'teenth':
        delta = 12 + DAYS[wkday] - date(yr, mth, 13).weekday()
    else:
        return False

    return date(yr, mth, delta + 1)
    
    
    
