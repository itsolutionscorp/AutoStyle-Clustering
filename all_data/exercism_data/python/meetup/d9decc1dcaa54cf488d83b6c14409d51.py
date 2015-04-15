from datetime import date
from calender import monthrange

def meetup_day(year, month, day, flag):
    days_num = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday': 6
    }

    days = [
        i
        for i in xrange(monthrange(year, month)[1])
        if date(year, month, i+1).weekday() == days_num[day]
    ]

    if flag == "teenth":
        days = [x for x in days if x >= 13 and x < 20]
        return date(year, month, days[0])
    elif flag == "last":
        return date(year, month, days[-1])
    else:
        pos = int(flag[0]) - 1
        return date(year, month, days[pos])
