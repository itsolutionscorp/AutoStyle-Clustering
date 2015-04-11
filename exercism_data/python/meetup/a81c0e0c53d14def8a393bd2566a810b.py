import datetime
import calendar

def meetup_day(y, m, wd, d):

    week_days = {
                'monday': 0,
                'tuesday': 1,
                'wednesday': 2,
                'thursday': 3,
                'friday': 4,
                'saturday': 5,
                'sunday': 6
                }
    count = 0

    if d == 'last':
        for i in range(calendar.monthrange(y, m)[1], 1, -1): 
            if datetime.date(y, m, i).weekday() == week_days[wd.lower()]:
                return datetime.date(y, m, i)
    
    if d[0].isdigit():
        for i in range(1, calendar.monthrange(y, m)[1]+1): 
            if datetime.date(y, m, i).weekday() == week_days[wd.lower()]:
                count += 1                
                if count == int(d[0]):            
                    return datetime.date(y, m, i)
    else:
         for i in range(13, 19):
            if datetime.date(y, m, i).weekday() == week_days[wd.lower()]:
                return datetime.date(y, m, i)
