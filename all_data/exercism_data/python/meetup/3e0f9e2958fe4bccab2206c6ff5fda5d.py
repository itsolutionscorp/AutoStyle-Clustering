from datetime import date

def meetup_day(year, month, daya, dayb):
    dayc = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday').index(daya)
    x = 1
    y = []
    z = 0
    def lastday(month):
        if month == 1 or month == 3 or month == 5 or month == 7 or month == 8 or month == 10 or month == 12:
            return 31
        elif month == 4 or month == 6 or month == 9 or month == 11:
            return 30
        else:
            if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0:
                return 29
            else:
                return 28
    while x <= lastday(month):
        if date(year, month, x).weekday() == dayc:
            y.append(x)
        x += 1
    if dayb == 'teenth':
        for day in y:
            if 12 < day < 20:
                z = day
    if dayb == '1st':
        z = y[0]
    if dayb == '2nd':
        z = y[1]
    if dayb == '3rd':
        z = y[2]
    if dayb == '4th':
        z = y[3]
    if dayb == 'last':
        z = y[len(y) - 1]
    
    return date(year, month, z)
