from datetime import date, timedelta

def meetup_day(year, month, day, num):
    
    ans = {'1st':range(1,8),
           '2nd':range(8,14),
           '3rd':range(14,21),
           'teenth':range(13,20),
           '4th':range(21,27),
           'last':reversed(range((date(year,month+1,1) - timedelta(7)).day,
                                 (date(year,month+1,1) - timedelta(1)).day + 1
                                 ))}
    weekdays =['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
    
    for i in ans[num]:
        if date(year,month,i).weekday() == weekdays.index(day):
            return date(year,month,i)
