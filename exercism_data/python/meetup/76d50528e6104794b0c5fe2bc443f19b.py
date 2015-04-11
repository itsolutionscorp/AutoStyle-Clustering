from calendar import Calendar

TEENTH = range(13, 20)
WEEKDAY = {"Monday": 0,
           "Tuesday": 1,
           "Wednesday": 2,
           "Thursday": 3,
           "Friday": 4,
           "Saturday": 5,
           "Sunday": 6}


def find_if(iterable, predicate):
    """Return the first element in iterable satisfying predicate."""
    for element in iterable:
        if predicate(element):
            return element


def meetup_day(year, month, name, desc):
    cal = Calendar()
    weekday = WEEKDAY[name]
    days = [day for day in cal.itermonthdates(year, month) if
            day.month == month and day.weekday() == weekday]

    if desc[0].isdigit():
        digit = int(desc[0])
        return days[digit - 1]
    elif desc == "last":
        return days[-1]
    elif desc == "teenth":
        return find_if(days, lambda x: x.day in TEENTH)
