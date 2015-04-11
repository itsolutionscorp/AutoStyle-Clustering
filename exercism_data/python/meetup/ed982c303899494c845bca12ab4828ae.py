import datetime

weekdays = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"]

def meetup_day(year, month, dayOfWeek, schedule):
    if schedule in ["1st", "first"]:
        date = datetime.date(year, month, 1)
        date = find(date, dayOfWeek)
    elif schedule in ["2nd", "second"]:
        date = datetime.date(year, month, 8)
        date = find(date, dayOfWeek)
    elif schedule in ["3rd", "third"]:
        date = datetime.date(year, month, 15)
        date = find(date, dayOfWeek)
    elif schedule in ["4th", "fourth"]:
        date = datetime.date(year, month, 22)
        date = find(date, dayOfWeek)
    elif schedule == "teenth":
        date = datetime.date(year, month, 13)
        date = find(date, dayOfWeek)
    elif schedule == "last":
        if month == 12:
            date = datetime.date(year + 1, 1, 1) - datetime.timedelta(days=1)
        else:
            date = datetime.date(year, month + 1, 1) - datetime.timedelta(days=1)
        date = find(date, dayOfWeek, last=True)
    else:
        raise ValueError
    return date

def find(date, dayOfWeek, last=False):
    weekday = date.weekday()
    weekdayReq = weekdays.index(dayOfWeek.lower())
    if not last:
        diff = weekdayReq - weekday
        if diff < 0:
            diff += 7
        date += datetime.timedelta(days=diff)
    else:
        diff = weekday - weekdayReq
        if diff < 0:
            diff += 7
        date -= datetime.timedelta(days=diff)
    return date
