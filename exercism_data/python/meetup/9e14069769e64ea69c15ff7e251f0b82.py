from datetime import timedelta, date
from calendar import monthrange

oneday = timedelta(days=1)
dayIndex = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}


def meetup_day(year, month, weekday, selector):
    selectorStart = {'1st':1, '2nd':8, '3rd':15, '4th':22, 'teenth':13, 'last':monthrange(year, month)[1]-6} # the first possible day of the month for any given selector

    day = date(year, month, selectorStart[selector])
    while day.weekday() != dayIndex[weekday]:
        day += oneday
    return day
