from datetime import date, timedelta
from calendar import weekday, monthrange

TEENTH = range(13, 20)
WEEKDAY = {"Monday": 0,
           "Tuesday": 1,
           "Wednesday": 2,
           "Thursday": 3,
           "Friday": 4,
           "Saturday": 5,
           "Sunday": 6}


def _nth(first_day, day_num, n):
    """Get the date of the nth weekday in a month.

    Parameters:
        first_day - first day of the month
        day_num   - weekday number to find
        n         - which repetition of day in month (1st, 2nd, 3rd, 4th)
    """
    first_day_num = first_day.weekday()

    missing_days = (day_num - first_day_num) % 7
    weeks = timedelta(7) * (n - 1)

    return first_day + timedelta(missing_days) + weeks


def _last(first_day, day_num):
    """Get the date of the last weekday of a month.

    Parameters:
        first_day - first day of the month
        day_num   - weekday number to find
    """
    year, month = first_day.year, first_day.month

    last_day = monthrange(year, month)[1]
    last_day = date(year, month, last_day)

    last_day_num = last_day.weekday()
    too_many = (day_num - last_day_num) % 7

    return last_day - timedelta(too_many)


def find_if(iterable, predicate):
    """Return the first element in iterable satisfying predicate."""
    for element in iterable:
        if predicate(element):
            return element


def _teenth(date_obj, day_num):
    """Get the 'teenth' day of the month that is a specific weekday."""
    year, month = date_obj.year, date_obj.month

    predicate = lambda d: weekday(year, month, d) == day_num
    day = find_if(TEENTH, predicate)

    return date(year, month, day)


def meetup_day(year, month, name, desc):
    """Find the date of the meetup.

    Parameters (non-obvious):
        name - name of the weekday
        desc - string describing the type of day, any of:
               (1st, 2nd, 3rd, 4th, last, teenth)
    """
    first_day = date(year, month, 1)
    day_num = WEEKDAY[name]

    if desc[0].isdigit():
        digit = int(desc[0])
        return _nth(first_day, day_num, digit)
    elif desc == "last":
        return _last(first_day, day_num)
    elif desc == "teenth":
        return _teenth(first_day, day_num)
