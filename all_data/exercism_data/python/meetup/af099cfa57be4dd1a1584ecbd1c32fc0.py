from datetime import timedelta, date
import calendar

def meetup_day(year, month, dotw, nth):
    weekdays = {'Monday':0,
                'Tuesday':1,
                'Wednesday':2,
                'Thursday':3,
                'Friday':4,
                'Saturday':5,
                'Sunday':6}
                
    start_day = {'1st':1,
                 '2nd':8,
                 '3rd':15,
                 '4th':22,
                 'teenth':13,
                 'last':calendar.monthrange(year,month)[1]-6}

    day = date(year, month, start_day[nth])
    while day.weekday() != weekdays[dotw]:
        day += timedelta(days=1)
    return day
