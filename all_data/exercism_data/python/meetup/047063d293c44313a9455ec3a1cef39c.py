__author__ = 'flux'
from datetime import date, timedelta
from calendar import monthrange
def meetup_day(year, month, day, type):
    dayNum=makeDayNum(day)
    dayCount = 0
    firstDay, numDays = monthrange(year, month)
    if type == 'last':
        start = date(year, month, numDays)
        dayCount -= findDayDistance(dayNum, start.weekday())
    else:
        start = date(year, month, 1)
        dayCount += findDayDistance(firstDay, dayNum)
    if type == '2nd':
        dayCount += 7
    elif type == '3rd':
        dayCount += 14
    elif type == '4th':
        dayCount += 21
    elif type == 'teenth':
        if dayCount > 4:
            dayCount += 7
        else:
            dayCount += 14

    start = start + timedelta(days=dayCount)
    return start

def makeDayNum(day):
    if day == 'Tuesday':
        dayNum=1
    elif day == 'Wednesday':
        dayNum=2
    elif day == 'Thursday':
        dayNum=3
    elif day == 'Friday':
        dayNum=4
    elif day == 'Saturday':
        dayNum=5
    elif day == 'Sunday':
        dayNum=6
    else:
        dayNum=0
    return dayNum

def findDayDistance(startDay, endDay):

    if endDay > startDay:
        dayCount = endDay - startDay
    elif startDay > endDay:
        dayCount = 7 - (startDay - endDay)
    else:
        dayCount = 0
    return dayCount
