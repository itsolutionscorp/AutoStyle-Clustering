from datetime import date, timedelta
MeetupDayException = Exception
weekdays = [ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
sequence_enum = { "1st": 0, "2nd": 1, "3rd": 2, "4th": 3, "5th": 4, "last": -1 }

def is_leap_year(year):
    if year%4 == 0:
        if year%100 == 0:
            if year%400 == 0:
                return True
            return False
        return True
    return False

def meetup_day(year, month, weekday, sequence):
    if is_leap_year(year):
        days_per_month = [31,29,31,30,31,30,31,31,30,31,30,31] 
    else:
        days_per_month = [31,28,31,30,31,30,31,31,30,31,30,31]

    weekday_count = []

    for day in range(1, days_per_month[month-1]+1):
        d = date(year, month, day)
        if weekdays[d.weekday()] == weekday:
            weekday_count.append(day)

    if sequence != "teenth":
        try:
            day = weekday_count[sequence_enum[sequence]]
            return_date = date(year, month, day)
        except MeetupDayException, e:
            raise e
    else:
        for num in weekday_count:
            if 20 > num > 12:
                try:
                    return_date = date(year, month, num)
                except MeetupDayException, e:
                    raise e

    return return_date
