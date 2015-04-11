import datetime

from calendar import monthrange

def meetup_day(year, month, day, when):
    days_dict = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, 'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, 'Sunday' : 6}
    
    if when == 'teenth':
        r = (13, 20)
    elif when == '1st':
        r = (1, 8)
    elif when == '2nd':
        r = (8, 15)
    elif when == '3rd':
        r = (15, 22)
    elif when == '4th':
        r = (22, 29)
    elif when == 'last':
        r = (monthrange(year, month)[1]-6, monthrange(year, month)[1]+1)
    
    for x in range(r[0], r[1]):
        if datetime.date(year, month, x).weekday() == days_dict[day]:
            return datetime.date(year, month, x)
