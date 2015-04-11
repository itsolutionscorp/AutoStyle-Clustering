from datetime import date

def leap(year):
    return (year % 4 == 0 and not year % 100 == 0) or year % 400 == 0

days_of_week = ['monday','tuesday','wednesday','thursday','friday','saturday','sunday']
days_of_months = [31,28,31,30,31,30,31,31,30,31,31,31]
days_of_months_leap = [31,29,31,30,31,30,31,31,30,31,31,31] 

def meetup_day(year,month,day_of_week,whichdate):
    
    datelist = []    
      
    dow_idx = days_of_week.index(str.lower(day_of_week))   
    first_day_of_month = date(year, month,1).weekday()
    
    if leap(year):
      dom = days_of_months_leap
    else:
      dom = days_of_months
    
    
    dt = (dow_idx -first_day_of_month) % 7 + 1
    datelist.append(dt)
    while True:    
      dt = dt + 7        
      if dt <= dom[month-1]:      
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

