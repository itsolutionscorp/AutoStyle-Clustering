import datetime


def meetup_day(year, month, day, nth):
    teenth = [13, 14, 15, 16, 17, 18, 19]
    weekdays = {
        'Sunday': 6,
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5
    }
    base = datetime.date(year, month, 1)

    # This does not feel like the best way to handle this, but it works

    if nth == 'teenth':
        range = [base + datetime.timedelta(days=x - 1) for x in teenth]
        for day_delt in range:
            if day_delt.weekday() == weekdays[day]:
                return day_delt
    elif nth == "last":
        base = datetime.date(year, month + 1, 1)
        range = [base - datetime.timedelta(days=x) for x in xrange(1, 7)]
        for day_delt in range:
            if day_delt.weekday() == weekdays[day]:
                return day_delt
    elif nth[:1].isdigit():
        weeks = int(nth[:1]) - 1
        range = [
            base + datetime.timedelta(days=x, weeks=weeks)
            for x in xrange(0, 7)
        ]
        for day_delt in range:
            if day_delt.weekday() == weekdays[day]:
                if day_delt.month != month:
                    raise Exception
                return day_delt
    else:
        return base
