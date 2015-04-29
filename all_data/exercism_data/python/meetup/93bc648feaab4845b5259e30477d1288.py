from calendar import Calendar

DAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday',
        'Sunday']

MEETING = ['1st', '2nd', '3rd', '4th']

TEENTH = range(13, 19)


def meetup_day(year, month, weekday, meeting):
    cal = Calendar().monthdatescalendar(year, month)
    dates = []

    for week in cal:
        if month == week[DAYS.index(weekday)].month:
            dates.append(week[DAYS.index(weekday)])

    if meeting == 'last':
        return dates[-1]
    elif meeting == 'teenth':
        date = [x for x in dates if x.day in TEENTH]
        return date[0]

    return dates[MEETING.index(meeting)]
