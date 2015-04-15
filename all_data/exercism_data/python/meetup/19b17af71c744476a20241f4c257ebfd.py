import datetime
import calendar

weekdays = list(calendar.day_name)
startDay = { "1st":1, "2nd":8, "3rd":15, "4th":22, "teenth":13 }

def meetup_day(year, month, dayOfWeek, schedule):
    if schedule == "last":
        date = datetime.date(year, month, calendar.monthrange(year, month)[1])
        date = find(date, dayOfWeek, last=True)
    else:
        if schedule not in startDay:
            raise ValueError
        date = datetime.date(year, month, startDay[schedule])
        date = find(date, dayOfWeek)
    return date

def find(date, dayOfWeek, last=False):
    weekday = date.weekday()
    weekdayReq = weekdays.index(dayOfWeek)
    diff = weekdayReq - weekday
    if last:
        if diff > 0:
            diff -= 7
    else:
        if diff < 0:
            diff += 7
    return date + datetime.timedelta(days=diff)
