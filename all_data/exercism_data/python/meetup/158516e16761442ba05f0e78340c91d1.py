import calendar
import datetime

def meetup_day(year, month, weekday, monthday):
    day = list(calendar.day_name).index(weekday)
    rel_mdays = [i for i in zip(*calendar.monthcalendar(year, month))[day] if i != 0]
    
    if monthday == 'teenth':
        mday = next((i for i in rel_mdays if i in range(13, 20)), None)
    else:
        week = -1 if monthday == 'last' else int(monthday[0]) - 1
        mday = rel_mdays[week]
        
    return datetime.date(year, month, mday)
