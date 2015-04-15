from datetime import date
import calendar

def meetup_day(year, month, day, day2):
    datedict = dict(zip(['Monday', 'Tuesday', 'Wednesday',
                         'Thursday', 'Friday', 'Saturday', 'Sunday'],
                          range(7)))
    datedict2 = dict(zip(['1st', '2nd', '3rd', '4th'], range(0, 4)))

    if day2 == "teenth":
        for x in range(13, 20):
            if datedict[day] == calendar.weekday(year, month, x):
                day3 = x

    elif day2 == "last":
        day3 = [x for x in range(1, calendar.monthrange(year, month)[1] + 1)
                if calendar.weekday(year, month, x) == datedict[day]][-1]
 
    else:
        day3 = [x for x in range(1, calendar.monthrange(year, month)[1] + 1)
                if calendar.weekday(year, month, x) == datedict[day]][datedict2[day2]]
    return date(year, month, day3)
