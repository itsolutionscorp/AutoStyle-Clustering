import calendar

def days_of_month(year, month):
    c = calendar.Calendar()

    for date in c.itermonthdates(year, month):
        if date.month == month:
            yield date

def days_of_month2(year, month, day):
    d = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'].index(day)

    for date in days_of_month(year, month):
        if date.weekday() == d:
            yield date

def meetup_day(year, month, day, which):
    days = list(days_of_month2(year, month, day))

    if which == "last":
        return days[-1]

    elif which == "teenth":
        for d in days:
            if 13 <= d.day <= 19:
                return d
    else:
        n = ['1st', '2nd', '3rd', '4th'].index(which)
        return days[n]
