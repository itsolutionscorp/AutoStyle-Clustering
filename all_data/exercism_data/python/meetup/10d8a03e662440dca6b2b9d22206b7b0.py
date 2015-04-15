#meetup.py
#Pleased to meet you

import datetime
import calendar

days = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}


def meetup_day(year, month, day, specifier):
    day_num = days[day]
    found = False
    if specifier == '1st':
        start = 1
    elif specifier == '2nd':
        start= 7
    elif specifier == '3rd':
        start= 14
    elif specifier == '4th':
        start= 21
    elif specifier == 'last':
        start=calendar.monthrange(year, month)[1]-6
    else:#teenth
        start= 13

    current = datetime.date(year, month, start)
    while not found:
        if current.weekday() == day_num:
            return current
        else:
            current=current+datetime.timedelta(days=1)
