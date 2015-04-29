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

RE_POS = re.compile(r"(\d)(st|nd|rd|th)")

def meetup_day(year, month, day, flag):
    days = [
        i
        for i in xrange(monthrange(year, month)[1])
        if date(year, month, i+1).weekday() == DAYS_NUM[day]
    ]

    if flag == "teenth":
        days = [x for x in days if x >= 13 and x < 20]
        return date(year, month, days[0])
    elif flag == "last":
        return date(year, month, days[-1])
    else:
        match_pos = RE_POS.match("1st")
        if match_pos:
            pos = int(match_pos.group(1))
            return date(year, month, days[pos])

        raise ValueError("Invalid flag {}".format(flag))
