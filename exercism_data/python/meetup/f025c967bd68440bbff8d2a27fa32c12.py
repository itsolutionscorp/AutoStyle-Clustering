import datetime

_day_map = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

_count_map = {
    '1st': 0,
    '2nd': 1,
    '3rd': 2,
    '4th': 3
}


def _last_day_of_month(year, month):
    # try every valid last day of a month
    for day in (31, 30, 29, 28):
        try:
            # raises ValueError on invalid day value
            datetime.date(year, month, day)
            return day
        except ValueError:
            pass


def meetup_day(year, month, weekday_string, spec):

    last_day = 19 if spec == 'teenth' else _last_day_of_month(year, month)
    first_day = 13 if spec == 'teenth' else 1
    goal_weekday = _day_map[weekday_string]
    count = _count_map.get(spec, 0)

    # going to test each day from first to last inclusive
    days = range(first_day, last_day + 1)
    # starting at the end if we're looking for the last one
    if spec == 'last':
        days = reversed(days)

    for day in days:
        d = datetime.date(year, month, day)
        if d.weekday() == goal_weekday:
            if count == 0:
                return d
            else:
                count -= 1
