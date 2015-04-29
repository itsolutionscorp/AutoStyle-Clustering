import datetime as date

def meetup_day(y, m, d, s):
    x = date.date(y, m, 1)
    dayDict = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
               'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    dayInt = dayDict[d]
    firstInt = x.weekday()
    
    dayDiff = max(firstInt, dayInt) - min(firstInt, dayInt)

    if dayInt >= firstInt:
        start = x + date.timedelta(dayDiff)
    else:
        start = x + date.timedelta(7 - dayDiff)

    if s == '1st':
       return start 
    elif s == '2nd' or s == 'teenth':
        if s == '2nd' or start.day > 5:
            return start + date.timedelta(7)
        else:
            return start + date.timedelta(14)
    elif s == '3rd':
        return start + date.timedelta(14)
    elif s == '4th':
        return start + date.timedelta(21)
    else:
        return start + date.timedelta(28)
