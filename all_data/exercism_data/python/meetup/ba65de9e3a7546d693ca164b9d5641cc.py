from datetime import date
from calendar import monthrange

weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 
            'Saturday': 5, 'Sunday': 6}


def meetup_day(year, month, weekday, rule):
    """Return date for meetup

    year, month -- integers for month when meetup takes place
    weekday -- string for weekday
    rule -- string, rule for the exact week, e.g. 'teenth' or '1st'
    """

    # each rule defines a potential range of days
    last_day = monthrange(year, month)[1]
    rulerange = {'1st': range(1, 8),
                 '2nd': range(8, 15),
                 '3rd': range(15, 22),
                 '4th': range(22, 29),
                 'last': range(last_day, 21, -1),
                 'teenth': range(13, 20)
                 }

    if rule not in rulerange:
        raise ValueError("This rule is not implemented.")

    for day in rulerange[rule]:
        if date(year, month, day).weekday() == weekdays[weekday]:
            return date(year, month, day)

    return False
