import calendar
from datetime import date

def meetup_day(year, month, meetDay, fuzzyWeek):
    weekdayMap = {"Monday": 0, "Tuesday":1, "Wednesday":2, "Thursday":3,
                   "Friday":4, "Saturday":5, "Sunday":6}
    calRange = calendar.monthrange(year,month) 

    if fuzzyWeek in ["1st", "2nd", "3rd", "4th"]:
        monthFirstWeekday = calRange[0]
        daysBehind = (weekdayMap[meetDay] - monthFirstWeekday)%7 
        meetDayFirst = date(year, month, 1 + daysBehind).day
        daysAddingMap = {"1st":0, "2nd":7, "3rd":14, "4th":21}
        addedDays = daysAddingMap[fuzzyWeek]
        meetDate =  date(year, month, meetDayFirst + addedDays) 
        return meetDate

    elif fuzzyWeek == "last":
        daysInMonth = calRange[1]
        monthLastDate = date(year, month, daysInMonth)
        daysAhead = (monthLastDate.weekday() - weekdayMap[meetDay])%7
        meetDate = date(year, month, daysInMonth - daysAhead)
        return meetDate 

    elif fuzzyWeek == "teenth":
        firstTeenth = 13
        theThirteenth = date(year, month, firstTeenth)
        daysBehind = (weekdayMap[meetDay] - theThirteenth.weekday())%7 
        meetDate = date(year, month, firstTeenth + daysBehind)
        return meetDate
