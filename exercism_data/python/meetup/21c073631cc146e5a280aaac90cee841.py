from calendar import Calendar, day_name
from datetime import date

ordMap = ('1st', '2nd', '3rd','4th')

def meetup_day(year,month,weekday,ord):
   
    d = [day[0] for day in Calendar().itermonthdays2(year,month) if day_name[day[1]] == weekday and day[0] != 0]

    if ord == 'teenth':
        return date(year,month,filter(lambda x: x > 12 and x < 20, d)[0])
    elif ord == 'last':
        return date(year,month,d[-1])
    else:
        return date(year,month,d[ordMap.index(ord)])
