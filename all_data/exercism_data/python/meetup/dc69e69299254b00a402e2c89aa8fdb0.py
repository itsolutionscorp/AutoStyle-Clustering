import datetime
import calendar

weekdays = list(calendar.day_name)

def meetup_day(year, month, dayOfWeek, schedule):
    if schedule == "last":
        date = datetime.date(year, month, calendar.monthrange(year, month)[1])
        date = find(date, dayOfWeek, last=True)
    else:
        if schedule in ["1st", "first"]:
            date = datetime.date(year, month, 1)
        elif schedule in ["2nd", "second"]:
            date = datetime.date(year, month, 8)
        elif schedule in ["3rd", "third"]:
            date = datetime.date(year, month, 15)
        elif schedule in ["4th", "fourth"]:
            date = datetime.date(year, month, 22)
        elif schedule == "teenth":
            date = datetime.date(year, month, 13)
        else:
            raise ValueError
        date = find(date, dayOfWeek)
    return date

def find(date, dayOfWeek, last=False):
    weekday = date.weekday()
    weekdayReq = weekdays.index(dayOfWeek)
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
