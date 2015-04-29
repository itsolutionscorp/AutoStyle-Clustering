from calendar import Calendar

DAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday',
        'Sunday']

MEETING = ['1st', '2nd', '3rd', '4th']

TEENTH = range(13, 19)


def meetup_day(year, month, weekday, meeting):
    cal = Calendar().itermonthdates(year, month)
    nweekday = DAYS.index(weekday)
    dates = [date for date in cal
             if date.month == month and date.weekday() == nweekday]

    if meeting == 'last':
        return dates[-1]
    elif meeting == 'teenth':
        return next(x for x in dates if x.day in TEENTH)
    else:
        return dates[MEETING.index(meeting)]
