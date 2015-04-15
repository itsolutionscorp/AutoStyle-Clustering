import calendar
import datetime

offset = {'1st': 0, '2nd': 7, '3rd': 14, '4th': 21, '5th': 28,
    'teenth': 12, 'last': -7}

def meetup_day(year, month, weekday, schedule):
    am = month
    if schedule == 'last':
        am = am%12+1
    d = datetime.date(year, am, 1)+datetime.timedelta(days=offset[schedule])
    d += datetime.timedelta((list(calendar.day_name).index(weekday)-d.weekday())%7)
    if d.month != month:
        raise Exception('no {} {} in {} {}'.format(schedule, weekday,
            calendar.month_name[month], year))
    return d
