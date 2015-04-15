from calendar import monthrange, day_name, weekday as getweekday
from datetime import date
import re

TEENTH = ('teenth', [13, 14, 15, 16, 17, 18, 19])

def meetup_day(year, month, weekday, day_indicator):
    weekday = list(day_name).index(weekday)

    expected_daycount = re.findall(r'\d+', day_indicator)
    current_daycount = 0

    days = xrange(1, monthrange(year, month)[1] + 1)
    if day_indicator is 'last': days = reversed(days)
    for day in days:
        # Find matching weekday
        if getweekday(year, month, day) is weekday:
            # Check for teenth
            if day_indicator is TEENTH[0] and day in TEENTH[1]:
                break
            # Check for last
            elif day_indicator is 'last':
                break
            # Check for daycount
            elif expected_daycount:
                current_daycount += 1
                if current_daycount is int(expected_daycount[0]):
                    break

    return date(year, month, day)
