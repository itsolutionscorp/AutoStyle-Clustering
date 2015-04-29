from datetime import date
from calendar import monthrange

weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 
            'Saturday', 'Sunday']


def meetup_day(year, month, weekday, rule):
    """Return date for meetup

    year, month -- integers for month when meetup takes place
    weekday -- string for weekday
    rule -- string, rule for the exact week, e.g. 'teenth' or '1st'
    """

    # each rule defines a potential range of days
    lastday = monthrange(year, month)[1]
    startday = {'1st': 1,
                '2nd': 8,
                '3rd': 15,
                '4th': 22,
                'last': lastday-6,
                'teenth': 13
                }

    if rule not in startday:
        raise ValueError("This rule is not implemented.")

    for day in range(startday[rule], startday[rule]+8):
        if date(year, month, day).weekday() == weekdays.index(weekday):
            return date(year, month, day)

    return False
