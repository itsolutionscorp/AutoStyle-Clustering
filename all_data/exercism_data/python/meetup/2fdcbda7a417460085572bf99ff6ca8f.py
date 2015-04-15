from datetime import date, timedelta
from calendar import monthrange

def meetup_day(year, month, dayOfWeek, selector):
    # 0 to 6 for day of week
    integerDayOfWeek = getWeekInt(dayOfWeek)
    daysInMonth = monthrange(year, month)[1]


    # This will handle basic cases - 1st, 2nd, 3rd, 4th, etc
    numberOfHitsRequired = getWhichHitCounter(selector)
    hitCounter = 0
    currentDay = 1
    while currentDay < daysInMonth:
        if date(year, month, currentDay).weekday() == integerDayOfWeek:
            hitCounter = hitCounter + 1
        if hitCounter == numberOfHitsRequired:
            return date(year, month, currentDay)
        currentDay = currentDay + 1

    # Now handle cases with 'last'
    if selector == 'last':
        currentDay = daysInMonth
        while currentDay > 1:
            if date(year, month, currentDay).weekday() == integerDayOfWeek:
                return date(year, month, currentDay)
            currentDay = currentDay - 1

    # Now handle teenth
    if selector == 'teenth':
        currentDay = 13
        while currentDay < 20:
            if date(year, month, currentDay).weekday() == integerDayOfWeek:
                return date(year, month, currentDay)
            currentDay = currentDay + 1

def getWhichHitCounter(s):
    if s == '1st':
        return 1
    elif s == '2nd':
        return 2
    elif s == '3rd':
        return 3
    elif s == '4th':
        return 4
    return None

def getWeekInt(dayOfWeek):
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday']
    for i, theday in enumerate(days):
        if theday == dayOfWeek:
            return i

    raise Exception("No day found")
