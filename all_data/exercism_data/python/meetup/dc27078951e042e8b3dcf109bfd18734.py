from datetime import date
from calendar import monthcalendar
def meetup_day(year, month, weekday, decider):
    week = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
    weekday = week.index(weekday)
    month_calendar = monthcalendar(year, month)
    if decider == 'teenth':
        for i in range(13, 20):
            if date(year, month , day=i).weekday() == weekday:
                return date(year, month , day=i)
    elif decider == 'last':
        weeks = len(month_calendar)
        if month_calendar[weeks-1][weekday] != 0:
            return date(year, month, month_calendar[weeks-1][weekday])
        else:
            return date(year, month, month_calendar[weeks-2][weekday])
    elif decider[0].isdigit():
        number = int(decider[0])
        if month_calendar[number-1][weekday] != 0 and  month_calendar[0][weekday] != 0:
            return date(year, month, month_calendar[number-1][weekday])
        else:
            return date(year, month, month_calendar[number][weekday])
