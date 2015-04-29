from datetime import datetime, date
from calendar import Calendar, monthrange

DoW = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}

def meetup_day(year, month, dow, criteria):
    target_day = DoW[dow]

    if criteria == "teenth":
        start_day = 13
    elif criteria =="last":
        start_day = monthrange(year, month)[1] - 6
    elif criteria.startswith("1"):
        start_day = 1
    elif criteria.startswith("2"):
        start_day = 8
    elif criteria.startswith("3"):
        start_day = 15
    elif criteria.startswith("4"):
        start_day = 22
    elif criteria.startswith("5"):
        start_day = 29
    else:
        raise KeyError

    cal = Calendar(datetime(year, month, 1).weekday())
    dates = cal.itermonthdays2(year, month)
    for day in dates:
        if day[0] >= start_day:
            if day[1] == target_day:
                return date(year,month,day[0])

    raise IndexError
