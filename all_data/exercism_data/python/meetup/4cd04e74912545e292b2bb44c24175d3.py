"""

"""
from datetime import date
from calendar import monthrange

def getDayOfWeek(day):
    #Takes in str day, returns numerical day value
    return {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}[day]

def meetup_day(year, month, day, repeatOn):
    if (repeatOn == 'teenth'):
        for i in xrange(13, 19):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
    elif (repeatOn == '1st'):
        for i in xrange(1,8):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
    elif (repeatOn == '2nd'):
        for i in xrange(8,15):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
    elif (repeatOn == '3rd'):
        for i in xrange(15,22):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
    elif (repeatOn == '4th'):
        for i in xrange(22,29):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
    elif (repeatOn == 'last'):
        daysInMonth = monthrange(year, month)[-1]
        for i in xrange(daysInMonth, daysInMonth - 7, -1):
            if (date(year, month, i).weekday() == getDayOfWeek(day)):
                return date(year, month, i)
