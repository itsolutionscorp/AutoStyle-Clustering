import datetime
import calendar

conditions = {'1st':1, '2nd':2, '3rd':3, '4th':4}

def meetup_day(year, month, day_name, condition):
    d = datetime.date(year, month, 1)
    weekday = get_weekday_from_name(day_name)
    meetup = next_weekday(d, weekday)
    if condition in conditions:
        meetup += datetime.timedelta(7 * (conditions[condition] - 1))
    elif condition == 'last':
        meetup = last_weekday(year, month, weekday)
    elif condition == 'teenth':
        meetup = teenth_weekday(year, month, weekday)
    return meetup

def teenth_weekday(year, month, weekday):
    for i in range(13, 20):
        d = datetime.date(year, month, i)
        if d.weekday() == weekday:
            return d

def last_weekday(year, month, weekday):
    cal = calendar.Calendar(0)
    month = cal.monthdatescalendar(year, month)
    lastweek = month[-1]
    return lastweek[weekday]

def next_weekday(d, weekday):
    days_ahead = weekday - d.weekday()
    if days_ahead < 0: # Target day already happened this week
        days_ahead += 7
    return d + datetime.timedelta(days_ahead)

def get_weekday_from_name(day_name):
    day_name = day_name.capitalize()
    return list(calendar.day_name).index(day_name)
