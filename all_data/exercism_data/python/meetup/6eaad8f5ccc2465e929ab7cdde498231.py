import datetime

from calendar import monthrange

def meetup_day(year, month, day, when):
    days_dict = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, 'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, 'Sunday' : 6}
    
    if when == 'teenth':
        start = 13
    elif when == '1st':
        start = 1
    elif when == '2nd':
        start = 8
    elif when == '3rd':
        start = 15
    elif when == '4th':
        start = 22
    elif when == 'last':
        start = monthrange(year, month)[1]-6
    
    for x in range(start, start+7):
        if datetime.date(year, month, x).weekday() == days_dict[day]:
            return datetime.date(year, month, x)
