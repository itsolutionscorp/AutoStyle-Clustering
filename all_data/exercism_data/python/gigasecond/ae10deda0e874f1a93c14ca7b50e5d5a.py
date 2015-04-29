#exact count of when do you reach your first gigasecond

from calendar import month
import leapInBetween
import datetime


def add_gigasecond(year, month,day):
    
    Fyear=year+31
    Fmonth=month+8 #taking simple 30 day months
    Fday=day+19+month(Fmonth,year)
    
    while(Fday>30):
        Fmonth+=1
        Fmonth-=30
    
    if(Fmonth>12):
        Fmonth=Fmonth-12
        Fyear+=1
    
    return  Fyear, Fmonth,Fday

#returns exact month missed days with leap years
def month (month,year):
    now = datetime.datetime.now()
    monthDays={1:31,2:28,3:31,4:30,5:31,6:30,7:31,8:31,9:30,10:31,11:30,1:31}
    
    days=leapInBetween(year,now.year)
    m=1
    while(m<month and m<13):
        if(monthDays[m]==31):
            days+=1
        elif(monthDays[m]==28):
            days-=2
        m+=1
            
        return days
    
    
