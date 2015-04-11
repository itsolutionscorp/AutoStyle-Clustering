from datetime import date, timedelta

def meetup_day(year, month, dayweek, extra):
    forward = True
    if extra == '1st':
        dat = date(year, month, 1)
    elif extra == '2nd':
        dat = date(year, month, 8)
    elif extra == '3rd':
        dat = date(year, month, 15)
    elif extra == '4th':
        dat = date(year, month, 23)
    elif extra == 'last':
        dat = date(year, month+1, 1) - timedelta(days=1)
        forward = False
    elif extra == 'teenth':
        dat = date(year, month, 13)
    else:
        return date(1,1,1)

    dw = -1
    if dayweek == 'Monday':
        dw = 0
    if dayweek == 'Tuesday':
        dw = 1
    if dayweek == 'Wednesday':
        dw = 2
    if dayweek == 'Thursday':
        dw = 3
    if dayweek == 'Friday':
        dw = 4
    if dayweek == 'Saturday':
        dw = 5
    if dayweek == 'Sunday':
        dw = 6

    while dat.weekday() != dw:
        if forward:
            dat += timedelta(days=1)
        else:
            dat -= timedelta(days=1)
    return dat
