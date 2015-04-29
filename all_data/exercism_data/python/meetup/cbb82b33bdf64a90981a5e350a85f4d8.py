from datetime import date
from calendar import Calendar, monthrange

weekdays = {"Monday": 0,
            "Tuesday": 1,
            "Wednesday": 2,
            "Thursday": 3,
            "Friday": 4,
            "Saturday": 5,
            "Sunday": 6}

def meetup_day(year, month, dayOfWeek, descriptor):
    dates = Calendar().itermonthdays2(year, month)

    validDates = filter(withinMonth, dates)
    filteredWeekdays = list(filter(filterTo(weekdays[dayOfWeek]), validDates))

    if descriptor == "1st":
        return date(year, month, filteredWeekdays[0][0])
    elif descriptor == "2nd":
        return date(year, month, filteredWeekdays[1][0])
    elif descriptor == "3rd":
        return date(year, month, filteredWeekdays[2][0])
    elif descriptor == "4th":
        return date(year, month, filteredWeekdays[3][0])
    elif descriptor == "last":
        return date(year, month, filteredWeekdays[-1][0])
    elif descriptor == "teenth":
        for dateOfMonth, dayOfWeek in filteredWeekdays:
            if dateOfMonth in range(13, 20):
                return date(year, month, dateOfMonth)

def withinMonth(dateTuple):
    if dateTuple[0] == 0:
        return False
    else:
        return True

def filterTo(weekday):
    def selectAllWeekday(dateTuple):
        if dateTuple[1] == weekday:
            return True
        else:
            return False
    return selectAllWeekday
