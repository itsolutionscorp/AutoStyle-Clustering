import datetime

def meetup_day(year, month, dayOfWeek, schedule):
    if schedule in ["1st", "first"]:
        date = datetime.date(year, month, 1)
        date = findFirst(date, dayOfWeek)
    elif schedule in ["2nd", "second"]:
        date = datetime.date(year, month, 8)
        date = findFirst(date, dayOfWeek)
    elif schedule in ["3rd", "third"]:
        date = datetime.date(year, month, 15)
        date = findFirst(date, dayOfWeek)
    elif schedule in ["4th", "fourth"]:
        date = datetime.date(year, month, 22)
        date = findFirst(date, dayOfWeek)
    elif schedule == "teenth":
        date = datetime.date(year, month, 13)
        date = findFirst(date, dayOfWeek)
    elif schedule == "last":
        if month == 12:
            date = datetime.date(year + 1, 1, 1) - datetime.timedelta(days=1)
        else:
            date = datetime.date(year, month + 1, 1) - datetime.timedelta(days=1)
        date = findLast(date, dayOfWeek)
    else:
        raise ValueError
    return date

def findFirst(date, dayOfWeek):
    while date.strftime("%A") != dayOfWeek:
        date += datetime.timedelta(days=1)
    return date

def findLast(date, dayOfWeek):
    while date.strftime("%A") != dayOfWeek:
        date -= datetime.timedelta(days=1)
    return date
