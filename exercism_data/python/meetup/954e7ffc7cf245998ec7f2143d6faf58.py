import calendar

CALENDAR = calendar.Calendar()
TEENTH = range(13, 20)
WEEKDAYS = [
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday',
]

def meetup_day(year, month, dow, which):
    dates_by_weekday = {}
    for dt in CALENDAR.itermonthdates(year, month):
        if dt.month == month:
            dates_by_weekday.setdefault(dt.weekday(), []).append(dt)

    weekday_dates = dates_by_weekday[WEEKDAYS.index(dow)]

    if which == 'last':
        return weekday_dates[-1]
    if which == 'teenth':
        return [
            dt for dt in weekday_dates
            if dt.day in TEENTH
        ][0]

    return weekday_dates[int(which[0])-1]
