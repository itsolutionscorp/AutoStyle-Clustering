from datetime import date
from calendar import monthrange


def meetup_day(year, month, weekday, whichone):
    weekdays = ["Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"]
    num_weekday = [i for i, d in enumerate(weekdays) if d == weekday][0]

    def right_weekday(date):
        return date.weekday() == num_weekday

    if whichone == "teenth":
        return [date(year, month, day) for day in range(10, 18)
                if right_weekday(date(year, month, day))][0]

    _, n_days = monthrange(year, month)

    if whichone == "last":
        return [date(year, month, day) for day in range(n_days - 6, n_days + 1)
                if right_weekday(date(year, month, day))][0]

    nth_occurrence = int(whichone[0])
    count = 0
    day = 0
    while count != nth_occurrence:
        day += 1
        if right_weekday(date(year, month, day)):
            count += 1

    return date(year, month, day)