import datetime
import calendar
teens = range(13,20)

def meetup_day(year, month, dayname, daynum):
    monthDayCount = calendar.monthrange(year,month)[1]
    dayTuples = [(dayName(year,month,day),day) for day in range(1,monthDayCount+1) if dayName(year,month,day) == dayname]
    if daynum == '1st':
        return datetime.date(year,month,dayTuples[0][1])
    elif daynum == '2nd':
        return datetime.date(year,month,dayTuples[1][1])
    elif daynum == '3rd':
        return datetime.date(year,month,dayTuples[2][1])
    elif daynum == '4th':
        return datetime.date(year,month,dayTuples[3][1])
    elif daynum == 'last':
        return datetime.date(year,month,dayTuples[-1][1])
    elif daynum == 'teenth':
        day = filter(lambda d: d[1] in teens, dayTuples)[0]
        return datetime.date(year,month,day[1])
    return Nothing

def dayName(year,month,day):
    return datetime.date(year,month,day).strftime('%A')
