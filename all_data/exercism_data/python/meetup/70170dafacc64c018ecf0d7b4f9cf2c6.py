import calendar
import time
from datetime import date

def meetup_day(year, month, weekday, rel):
    c = calendar.monthcalendar(year, month)
    wd = time.strptime(weekday[:3], "%a").tm_wday
    if rel[0].isdigit():
            if c[0][wd]:
                day = c[int(rel[0])-1][wd]
            else:
                day = c[int(rel[0])][wd]
    elif rel == 'last':
        day = c[-1][wd]
    elif rel == 'teenth':
        if 13 in c[1] and c[1][wd] >= 13:
            day = c[1][wd]
        else:
            day = c[2][wd]            
    
    return date(year, month, day)
