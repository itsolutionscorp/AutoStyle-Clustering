from datetime import date
from calendar import monthrange

days_of_week = ['monday','tuesday','wednesday','thursday','friday','saturday','sunday']

def meetup_day(year,month,day_of_week,whichdate):
    
    datelist = []    
      
    dow_idx = days_of_week.index(str.lower(day_of_week))   
    first_day_of_month = monthrange(year,month)[0]
        
    dt = (dow_idx -first_day_of_month) % 7 + 1
    datelist.append(dt)
    while True:    
      dt = dt + 7        
      if dt <= monthrange(year,month)[1]:      
        datelist.append(dt)
      else:
        break
      
    
    if len(whichdate) == 3:
     day = date(year,month,datelist[int(whichdate[0]) - 1])    
    elif whichdate == 'teenth':
     day = date(year,month,[x for x in datelist if x>=12 and x <=19][0])
    elif whichdate == 'last':
     day = date(year,month,datelist[-1])

    return day

