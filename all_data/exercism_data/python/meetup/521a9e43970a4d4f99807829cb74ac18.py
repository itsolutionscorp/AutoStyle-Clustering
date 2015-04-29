import datetime
import calendar

WEEKLIST = list(calendar.day_name)

def meetup_day(year, month, dayOfWeek, dayType):
    weekday_index = WEEKLIST.index(dayOfWeek.title())
    num_days = calendar.monthrange(year, month)[1]
    days = []
    for day in range(1, num_days + 1):
        if datetime.date(year, month, day).weekday() == weekday_index:
            days.append(day)
    
    if dayType.lower() == 'teenth':
        for day in days:
            if 12 < day < 20:
                return datetime.date(year, month, day)

    if dayType.lower() == '1st' or dayType.lower() == 'first':
        return datetime.date(year, month, days[0])

    if dayType.lower() == 'last':
        return datetime.date(year, month, days[-1])

    if dayType.lower() == '2nd' or dayType.lower() == 'second':
        return datetime.date(year, month, days[1])

    if dayType.lower() == '3rd' or dayType.lower() == 'third':
        return datetime.date(year, month, days[2])

    if dayType.lower() == '4th' or dayType.lower() == 'fourth':
        return datetime.date(year, month, days[3])

    if dayType.lower() == '5th' or dayType.lower() == 'fifth':
        return datetime.date(year, month, days[4])
