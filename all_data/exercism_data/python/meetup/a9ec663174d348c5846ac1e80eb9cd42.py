# 

import datetime
import calendar
    


dow = dict(zip('Monday Tuesday Wednesday Thursday Friday Saturday Sunday'.split(),
           range(7)))
def meetup_day(year, month, weekday, which):
    
    if which == 'teenth':
        interval = range(13,20)
    elif which == '1st':
        interval = range(1,8)
    elif which == '2nd':
        interval = range(8,15)
    elif which == '3rd':
        interval = range(15,22)
    elif which == '4th':
        interval = range(22,29)
    elif which == 'last':
        last_day_of_month = calendar.monthrange(year, month)[1]
        interval = range(last_day_of_month,last_day_of_month-7,-1)
    for i in interval:
        if datetime.date.weekday(datetime.date(year, month, i))== dow[weekday]:
            return datetime.date(year, month, i)
