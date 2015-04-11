from datetime import date
from calendar import monthrange

days_of_week = ['monday','tuesday','wednesday','thursday','friday','saturday','sunday']

def meetup_day(year,month,day_of_week,whichdate):
    
    dow_idx = days_of_week.index(str.lower(day_of_week))   
    
    datelist = [dy for (d,dy) in zip([(x+monthrange(year,month)[0]-1) % 7 for x in range(1,monthrange(year,month)[1]+1)],[y for y in range(1,monthrange(year,month)[1]+1)]) if d == dow_idx]

   
      
    
    if len(whichdate) == 3:
     day = date(year,month,datelist[int(whichdate[0]) - 1])    
    elif whichdate == 'teenth':
     day = date(year,month,[x for x in datelist if x>=12 and x <=19][0])
    elif whichdate == 'last':
     day = date(year,month,datelist[-1])

    return day

