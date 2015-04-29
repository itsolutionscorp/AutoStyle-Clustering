import datetime
import time

def meetup_day(year, month, dayName, count):
    if count == '1st':
        dateMin = 1
        dateMax = 7
    elif count == '2nd':
        dateMin = 8
        dateMax = 14
    elif count == 'teenth':
        dateMin = 13
        dateMax = 19
    elif count == '3rd':
        dateMin = 15
        dateMax = 21
    elif count == '4th':
        dateMin = 22
        dateMax = 28
    elif count == '5th':
        dateMin = 29
        dateMax = 31
    elif count == 'last':
        dateMin = 25
        dateMax = 31
    else:
        return "Bad combination"

    week = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
    dayNum = week.index(dayName)

    while datetime.date.weekday(datetime.date(year,month,dateMin)) != dayNum:
##        print "Weekday: " + str(datetime.date(year,month,dateMin))
##        print "Day Number: " + str(dayNum)
##        print "Date being tested: " + str(dateMin)
        dateMin += 1       
##    print "Post-Compare " + str(dateMin)
##    print dayNum
    return datetime.date(year, month, dateMin)


