from datetime import date

def meetup_day(year, month, weekday, date_str):
    
    weekdays={'Monday':1,'Tuesday':2,'Wednesday':3,'Thursday':4,
              'Friday':5,'Saturday':6,'Sunday':7}
    day_number=weekdays[weekday]
    if date_str=='teenth':
        for n in range(13,20):
            #print n,day_number,date(year,month,n).day
            if day_number==date(year,month,n).isoweekday():
                return date(year,month,n)
    elif date_str=='last':
        for n in range(31,20,-1):
            try:
                date(year,month,n)
            except:
                continue
            return date(year,month,n)
    else:
        time = int(date_str[0])
        count = 0
        for n in range(1,32):
            #print n,count,time
            if count<time:
                if day_number==date(year,month,n).isoweekday():
                    count+=1
            else:
                return date(year,month,n-1)
                
            
