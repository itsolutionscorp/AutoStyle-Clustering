from datetime import date
from calendar import monthrange

days = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

def span(year, month, spec):
    monthlength = monthrange(year, month)[1]
    if spec == '1st':
        return range(1, 8)
    elif spec == '2nd':
        return range(8, 15)
    elif spec == '3rd':
        return range(15, 22)
    elif spec == 'teenth':
        return range(13, 20)
    elif spec == '4th':
        return range(22, 29)
    else:
        return range((monthlength - 6), monthlength + 1)
        
def week(year, month, spec):
    dates = []
    for i in span(year, month, spec):
        dates.append(date(year, month, i))
    return dates
    
 
def meetup_day(year, month, day, spec):
    for meeting in week(year, month, spec):
        if date.weekday(meeting) == days[day]:
            return meeting
