import datetime

def meetup_day(year, month, weekday, position):
    weekdays = [
        "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday",
        "Sunday"
    ]
    targ_weekday = weekdays.index(weekday)
    
    ### Default is teenth
    targ_pos = -1
    if position[0].isdigit():
        targ_pos = int(position[0]) - 1
    
    ### Try until date not found
    date = 1
    dates = []
    while True:
        try:
            day = datetime.date(year, month, date)
            
            if day.weekday() == targ_weekday:
                dates.append(day)
                date += 7
            else:
                date += 1
        except:
            break
    
    if position=="teenth":
        for date in dates:
            if 10<date.day<20:
                return date
    
    return dates[targ_pos]
