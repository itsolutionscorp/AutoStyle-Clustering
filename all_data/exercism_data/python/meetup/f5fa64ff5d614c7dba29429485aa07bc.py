from datetime import date
from calender import monthrange
import re

DAYS_NUM = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

def meetup_day(year, month, day, flag):
    days = [
        i
        for i in xrange(monthrange(year, month)[1])
        if date(year, month, i+1).weekday() == DAYS_NUM[day]
    ]

    match = re.match(
        r"(teenth|last|(\d)(st|nd|rd|th))",
        flag
    )

    if match.group(0) == "teenth":
        days = [x for x in days if x >= 13 and x < 20]
        return date(year, month, days[0])
    elif match.group(0) == "last":
        return date(year, month, days[-1])
    elif match.group(1) in range(1, 4):
        pos = int(match.group(1))
        return date(year, month, days[pos])
    else:
        raise ValueError("Invalid flag {}".format(flag))
