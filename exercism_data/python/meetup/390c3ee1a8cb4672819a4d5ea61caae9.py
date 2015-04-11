import datetime
from calendar import monthrange

teenth = range(13,20)
    
days = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, \
             'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, \
             'Sunday' : 6}


def meetup_day(year,month,namedday, suffix):
    if suffix == 'teenth':
        for day in teenth:
            date = datetime.date(year,month,day)
            if days[namedday] == date.weekday():
                return date
                
    if suffix[0].isdigit():
        weekday = (int(suffix[0]) - 1) * 7
        for day in range(1,8):
            date = datetime.date(year,month,day+weekday)
            if days[namedday] == date.weekday():
                return date
    
    if suffix == 'last':
        monthlen = monthrange(year,month)[1]
        for day in range(monthlen, monthlen -7, -1):
            date = datetime.date(year,month,day)
            if days[namedday] == date.weekday():
                return date
        
        
    

meetup_day(2013, 10, 'Thursday', 'last')
meetup_day(2012, 2, 'Wednesday', 'last')
