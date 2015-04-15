import datetime

weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
deltas = {'1st': 0, '2nd': 7, '3rd': 14, '4th': 21}

def meetup_day(year, month, weekday, daytype):
    if daytype == 'teenth':
        return meetup_day_teenth(year, month, weekday)
    d = meetup_day_firstwd(year, month, weekday)
    if daytype == 'last':
        return meetup_last_wd(year, month, weekday)
    if daytype == 'first':
        return d
    return d + datetime.timedelta(days=deltas[daytype])

def meetup_day_teenth(year, month, weekday):
    d = datetime.date(year, month, 13)
    delta = weekdays[weekday] - d.weekday()
    if(delta < 0):
        delta += 7
    return d + datetime.timedelta(days=delta)

def meetup_day_firstwd(year, month, weekday):
    d = datetime.date(year, month, 1)
    delta = weekdays[weekday] - d.weekday()
    if(delta < 0):
        delta += 7
    return d + datetime.timedelta(days=delta)

def meetup_last_wd(year, month, weekday):
    first_next_month = 0
    if(month == 12):
        first_next_month = meetup_day_firstwd(year + 1, 1, weekday)
    else:
        first_next_month = meetup_day_firstwd(year, month + 1, weekday)
    return first_next_month + datetime.timedelta(weeks=-1)
