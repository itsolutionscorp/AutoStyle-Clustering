from datetime import date
from calender import monthrange

DAYS_NUM = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

def meetup_day(year, month, day, flag):
    days = [
        i
        for i in xrange(monthrange(year, month)[1])
        if date(year, month, i+1).weekday() == DAYS_NUM[day]
    ]

    if flag == "teenth":
        days = [x for x in days if x >= 13 and x < 20]
        return date(year, month, days[0])
    if flag == "last":
        return date(year, month, days[-1])
    if flag in ["1st", "2nd", "3rd", "4th"]:
        pos = int(flag[0])
        return date(year, month, days[pos])
    raise ValueError("Invalid flag {}".format(flag))
