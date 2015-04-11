import datetime
import calendar

def meetup_day(year, month, dayText, meetupType):
    
    dayNum = convertDayToNum(dayText)
    myDate = datetime.date(year, month, 1)
    while myDate.weekday() != dayNum:
        myDate = myDate + datetime.timedelta(1)

    if meetupType == '2nd':
        myDate = myDate + datetime.timedelta(7)
    elif meetupType == '3rd':
        myDate = myDate + datetime.timedelta(14)
    elif meetupType == '4th':
        myDate = myDate + datetime.timedelta(21)
    elif meetupType == 'teenth':
        while myDate.day < 12:
            myDate = myDate + datetime.timedelta(7)
    elif meetupType == 'last':
        myDate = datetime.date(year, month, calendar.monthrange(year, month)[1])
        while myDate.weekday() != dayNum:
            myDate = myDate + datetime.timedelta(-1)

    return myDate


def convertDayToNum(dayText):
    if dayText == 'Monday':
        return 0
    if dayText == 'Tuesday':
        return 1
    if dayText == 'Wednesday':
        return 2
    if dayText == 'Thursday':
        return 3
    if dayText == 'Friday':
        return 4
    if dayText == 'Saturday':
        return 5
    if dayText == 'Sunday':
        return 6
