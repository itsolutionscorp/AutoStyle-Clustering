from datetime import date
from calendar import monthrange


def meetup_day(year, month, weekday, order):
    iso_day_nr = {"Monday": 1, "Tuesday": 2, "Wednesday": 3, "Thursday": 4,
                  "Friday": 5, "Saturday": 6, "Sunday": 7}
    dates = []
    for i in range(1, monthrange(year, month)[1]+1):
        if date(year, month, i).isoweekday() == iso_day_nr[weekday]:
            dates.append(date(year, month, i))
        else:
            continue

    positions = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3, "last": len(dates)-1}
    if order == "teenth":
        for i in dates:
            if i.day > 12 and i.day < 20:
                return i
    else:
        return dates[positions[order]]
