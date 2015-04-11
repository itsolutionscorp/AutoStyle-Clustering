def add_gigasecond(givedate):
    import datetime
    
    def is_leap_year(year):
        leapyear = False
        if year % 4 == 0:
            leapyear=True
        if year % 100 == 0:
            leapyear = False
        if year % 400 == 0:
            leapyear = True
        return leapyear

    def days_in_month(month,year):
        if month in (1,3,5,7,8,10,12):
            if day-31<0:
                return 31
        if month in (4,6,9,11):
            if day-30<0:
                return 30
        if month == 2:
            if is_leap_year(year):
                return 29
            else:
                return 28

    daytime=10**9/60/60/24
    year = givedate.year
    month = givedate.month
    day = givedate.day

    daytime-=days_in_month(month,year)-day
    day=0
    month+=1
    if month==13:
        year+=1
        month=1
    
    while daytime>30:
        daytime-=days_in_month(month,year)
        month+=1
        if month==13:
            year+=1
            month=1
    day+=daytime 
    
    return datetime.date(year,month,day)
