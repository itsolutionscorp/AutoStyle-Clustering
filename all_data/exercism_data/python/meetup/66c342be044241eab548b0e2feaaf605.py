def meetup_day(year, month, day, meettime):
    import datetime
    daynum=1
    dt = datetime.date(year,month,daynum)
    while dt.strftime("%A")!=day:
        daynum+=1
        dt = datetime.date(year,month,daynum)
    
    if meettime=='1st':
        return dt
    if meettime=='2nd':
        return datetime.date(year,month,daynum+7)
    if meettime=='3rd':
        return datetime.date(year,month,daynum+14)
    if meettime=='4th':
        return datetime.date(year,month,daynum+21)
    if meettime=='last':
        return datetime.date(year,month,daynum+28)
    if meettime=='teenth':
        if daynum<6:
            return datetime.date(year,month,daynum+14)
        else:
            return datetime.date(year,month,daynum+7)
