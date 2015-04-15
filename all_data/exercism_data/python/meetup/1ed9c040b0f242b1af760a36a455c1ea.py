#calculates teh day of a meetup considering:
#Typically meetups happen on the same day of the week.

import datetime
import calendar

#Ex:meetup_day(2013, 5, 'Monday', 'teenth')
def meetup_day(year,month,day,prefix):
    DayDate=0
    
    if(prefix=='1st'):
        DayDate= getDay(year,month,1,day,1)
    elif(prefix=='2nd'):
        DayDate= getDay(year,month,2,day,1)  
    elif(prefix=='3rd'):
        DayDate= getDay(year,month,3,day,1)
    elif(prefix=='4th'):
        DayDate= getDay(year,month,4,day,1)
    elif(prefix=='teenth'):
        DayDate= getDay(year,month,1,day,10)
    
    return datetime.date(year,month,DayDate)

#returns a day acording to a prefix week
def getDay(year,month,prefix,day,start):   
    monthDays={1:31,2:28,3:31,4:30,5:31,6:30,7:31,8:31,9:30,10:31,11:30,1:31}
    monthdays=monthDays[month]+1;
    d=start
    found=0 #counts the weekday
    
    while(d <monthdays):
        dNow= datetime.date(year,month,d)
        if(dNow.strftime("%A")==day):
            found+=1
            if(found==prefix):
                return d
        d+=1
        
