import datetime


from datetime import date


def meetup_day(year, month, daystr, dayending):
    day = 0
    if daystr == 'Monday':
        day = 0
    elif daystr == 'Tuesday':
        day = 1
    elif daystr == 'Wednesday':
        day = 2
    elif daystr == 'Thursday':
        day = 3
    elif daystr == 'Friday':
        day = 4
    elif daystr == 'Saturday':
        day = 5
    elif daystr == 'Sunday':
        day = 6
    daysInTheMonth = 28 if month == 2 else (30 if month % 2 == 0 and month != 2 else 31)
    dayOfTheMonth = 1
    if dayending == 'last':
        while daysInTheMonth > dayOfTheMonth:
            dayOfTheWeek = datetime.datetime(year, month, daysInTheMonth + 1).weekday()
            if dayOfTheWeek == day:
                return date(year, month, daysInTheMonth + 1)
            daysInTheMonth -= 1

    occurence = 0
    while dayOfTheMonth < daysInTheMonth:
        dayOfTheWeek = datetime.datetime(year, month, dayOfTheMonth).weekday()
        if dayOfTheWeek == day:
            if dayending == 'teenth':
                if 12 < dayOfTheMonth < 20:
                    break
            if dayending == '1st':
                break
            if dayending == '2nd':
                if occurence == 1:
                    break
                occurence += 1
            if dayending == '3rd':
                if occurence == 2:
                    break
                occurence += 1
            if dayending == '4th':
                if occurence == 3:
                    break
                occurence += 1
        dayOfTheMonth += 1
    return date(year, month, dayOfTheMonth)
