from datetime import date

def add_gigasecond(date_to_convert):
    year=date_to_convert.year
    month=date_to_convert.month
    day=date_to_convert.day
    day=day+11574
    while day>31:
        if month==1 or month==3 or month==5 or month==7 or month==8 or month==10 or month==12:
            day=day-31
            month=month+1
        elif month==4 or month==6 or month==9 or month==11:
            day=day-30
            month=month+1
        elif month==2 and (year%4==0 and year%100!=0 or year%400==0):
            day=day-29
            month=month+1
        else:
            day=day-28
            month=month+1
        if month>12:
            month=month-12
            year=year+1
    return date(year, month, day)
    if (month==4 or month==6 or month==9 or month==11) and day==31:
        day=day-30
        month=month+1
    elif (month==2 and (year%4==0 and year%100!=0 or year%400==0)) and day>29:
        day=day-29
        month=month+1
    elif month==2 and day>28:
        day=day-28
        month=month+1
    if month>12:
        month=month-12
        year=year+1
    return date(year, month, day)
            
