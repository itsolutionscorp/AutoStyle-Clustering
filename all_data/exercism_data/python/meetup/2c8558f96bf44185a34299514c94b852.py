__author__ = 'Eric'

from datetime import date
import calendar


def meetup_day(year, month, weekday, occurrence):
    weekdays = {'Monday':0,
                'Tuesday':1,
                'Wednesday':2,
                'Thursday':3,
                'Friday':4,
                'Saturday':5,
                'Sunday':6}
    weekday = weekdays[weekday]

    occurrences = {'1st':0,
                   '2nd':1,
                   '3rd':2,
                   '4th':3}

    first_wday = calendar.monthrange(year, month)[0]
    last_day = calendar.monthrange(year, month)[1]

    if weekday < first_wday:
        start = 1 + 7 - abs(weekday - first_wday)
    else:
        start = 1 + weekday - first_wday

    if occurrence in occurrences.keys():
        return date(year, month, start + occurrences[occurrence]*7)

    if occurrence == 'teenth':
        for i in range (11, 19):
            if date(year, month, i).weekday() == weekday:
                return date(year, month, i)

    if occurrence == 'last':
        while True:
            if date(year, month, last_day).weekday() == weekday:
                return date(year, month, last_day)
            else:
                last_day -= 1
