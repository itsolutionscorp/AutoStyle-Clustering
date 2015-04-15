from datetime import date

def is_leap_year(year):
    return (year % 4 == 0 and year % 400 == 0) or (year % 4 == 0 and year % 100 != 0)

def last_day_month(year,month):
    if month in [4,6,9,11]: return 30
    elif month != 2: return 31
    elif is_leap_year(year): return 29
    else: return 28
    
def meetup_day(year, month, day, num):
    
    ans = {'1st':range(1,8),
           '2nd':range(8,14),
           '3rd':range(14,21),
           'teenth':range(13,20),
           '4th':range(21,27),
           'last':reversed(range(last_day_month(year,month)-7,last_day_month(year,month)+1))}
    
    weekdays =['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
    
    for i in ans[num]:
        if date(year,month,i).weekday() == weekdays.index(day):
            return date(year,month,i)
