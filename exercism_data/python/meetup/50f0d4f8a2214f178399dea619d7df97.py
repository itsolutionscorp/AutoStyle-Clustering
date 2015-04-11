from calendar import monthrange
from datetime import date

DAYS = {
    "Monday": 1,
    "Tuesday": 2,
    "Wednesday": 3,
    "Thursday": 4,
    "Friday": 5,
    "Saturday": 6,
    "Sunday": 7
}


def meetup_day(year, month, day_name, which):
    month_length = monthrange(year, month)[1]
    days = [date(year, month, d) for d in range(1, month_length + 1)]
    days = [d for d in days if d.isoweekday() == DAYS[day_name]]

    if which == "teenth":
        return [d for d in days if 13 <= d.day <= 19][0]
    elif which == "last":
        return days[-1]
    return days[int(which[0]) - 1]
