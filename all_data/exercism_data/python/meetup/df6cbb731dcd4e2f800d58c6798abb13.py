from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, day):
    wd_list=['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
    day_list=[]
    
    for i in range(monthrange(year,month)[1]):
        if date(year,month,i+1).weekday()==wd_list.index(weekday):
            day_list+=[i+1]

    if day=='1st': return date(year,month,day_list[0])
    if day=='2nd': return date(year,month,day_list[1])
    if day=='3rd': return date(year,month,day_list[2])
    if day=='4th': return date(year,month,day_list[3])
    if day=='last': return date(year,month,day_list[-1])

    if day=='teenth':
        for i in range(len(day_list)):
            if day_list[i]<20 and day_list[i]>12: return date(year,month,day_list[i])
