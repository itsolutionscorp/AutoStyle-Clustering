from datetime import date
import calendar 

def meetup_day(year,month,day,type):
    dow={'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':
6}
    tow={'1st':1,'2nd':2,'3rd':3,'4th':4,'teenth':2,'last':4}
    
    monthcal=calendar.monthcalendar(year,month)
    weekcounter=0
    if type=='last':
        monthcal.reverse()
    for week in monthcal:
        if type=='teenth' and week[dow[day]]>=13 and week[dow[day]]<=19:
              return date(year,month,week[dow[day]])
        elif type=='last' and  week[dow[day]]!=0:
            return date(year,month,week[dow[day]])
        elif type!='teenth' and  week[dow[day]]!=0 :
            weekcounter+=1
            if weekcounter==tow[type]:
                return date(year,month,week[dow[day]])
        else:
            continue            
