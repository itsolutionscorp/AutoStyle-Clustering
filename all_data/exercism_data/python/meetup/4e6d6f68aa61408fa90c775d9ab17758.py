import datetime
def meetup_day(year, month, day, times):
    normalized_day = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}[day]

    first_day_of_month = datetime.date(year, month, 1)
    offset = normalized_day - first_day_of_month.weekday()
    if offset < 0:
        offset += 7

    thirtenth_day_of_month = datetime.date(year, month, 13)
    thirten_offset = normalized_day - thirtenth_day_of_month.weekday()
    if thirten_offset < 0:
        thirten_offset += 7

    next_month = month + 1
    if (next_month > 12):
        month = 1
        year += 1
    
    last_day_of_month = datetime.date(year, next_month,1)-datetime.timedelta(days=1)
    last_offset=normalized_day - last_day_of_month.weekday()
    

    if times == "1st":    
        return  first_day_of_month+datetime.timedelta(days=offset)
    if times == "2nd":    
        return  first_day_of_month+datetime.timedelta(days=offset+7)
    if times == "3rd":    
        return  first_day_of_month+datetime.timedelta(days=offset+14)
    if times == "4th":    
        return  first_day_of_month+datetime.timedelta(days=offset+21)
    if times == "5th":    
        return  first_day_of_month+datetime.timedelta(days=offset+28)
    if times == "teenth":    
        return  thirtenth_day_of_month+datetime.timedelta(days=thirten_offset)

    if times == "last":    
        return  last_day_of_month+datetime.timedelta(days=last_offset) #"+" since last_offset is negative


    return datetime.today()
