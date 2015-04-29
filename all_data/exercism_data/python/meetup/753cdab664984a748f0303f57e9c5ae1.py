from datetime import date, timedelta
import calendar

weekday = dict(zip("Monday Tuesday Wednesday Thursday Friday Saturday Sunday".split(), range(7)))

def meetup_day(year, month, wday, nth_day):
    firstday, days_in_month = calendar.monthrange(year, month)
    day_delta = weekday[wday] - firstday
    init_day = (day_delta >= 0) and (day_delta + 1) or (day_delta + 8)

    days = []

    while init_day <= days_in_month:
        days.append(init_day)
        init_day += 7

    nth_day = nth_day.lower()

    if nth_day in ["1st", "first"]:
        day = days[0]
    elif nth_day in ["2nd", "second"]:
        day = days[1]
    elif nth_day in ["3rd", "third"]:
        day = days[2]
    elif nth_day in ["4th", "fourth"]:
        day = days[3]
    elif nth_day in ["5th", "fifth"]:
        day = days[4]
    elif nth_day == "last":
        day = days[-1]
    elif nth_day == "teenth":
        day = [d for d in days if d > 12 and d < 20][0]
    else:
        return None

    return date(year, month, day)
