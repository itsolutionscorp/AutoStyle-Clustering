import calendar

# For some reason I found this challenge really difficult for some reason.

_days = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

def meetup_day(year, month, dow, day):
    cal = calendar.Calendar()
    month_cal = cal.monthdatescalendar(year, month)

    if 'last' in day:
        return find_last_dow(dow, month_cal)
    if 'teenth' in day:
        return find_teenth(dow, month_cal)
    if day in ('1st', '2nd', '3rd', '4th'):
        d = int(day[0])
        # i give up :(
        if d == 2:
            d = d - 1
        return calendar.Calendar(_days[dow]).monthdatescalendar(year, month)[d][0]

def find_last_dow(dow, month):
    lastweek = month[-1]
    return lastweek[_days[dow]]

def find_teenth(dow, month):
    for w in month:
        for d in w:
            if d.weekday() == _days[dow] and d.day in range(13, 19):
                return d
    raise Exception('FAIL :(')
