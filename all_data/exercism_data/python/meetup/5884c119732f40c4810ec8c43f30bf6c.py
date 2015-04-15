import datetime

days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
                'Saturday', 'Sunday']
day_lookup = dict(zip(days_of_week, range(8)))


def days_in_month(month, year):
    """Get the number of days in a given month."""
    next_month = datetime.date(year, month+1, 1)  # might be leap year
    last_day = next_month - datetime.timedelta(days=1)
    return last_day.day


def compute_offset(weekday1, weekday2):
    """Compute the number of days left from weekday1 to weekday2.
    Example: Monday (0) to Wednesday (2) -> 2, Wednesday to Monday -> 5.
    """
    if weekday1 < weekday2:
        offset = weekday2 - weekday1
    elif weekday1 > weekday2:
        offset = 7 - weekday1 + weekday2
    else:
        offset = 0
    return offset


def meetup_day(year, month, day, q):
    # Compute a convenient starting point for counting, based on the type of
    # query `q` that is performed.
    if q[0].isdigit():
        nth = int(q[0])  # the `nth` week
        offset = 1 + (nth - 1) * 7
    elif q == "teenth":
        offset = 13  # the 'teenth' days start at 'thirteenth'
    elif q == "last":
        offset = days_in_month(month, year) - 6  # first day of last week
    else:
        return

    # Convert starting date to a datetime.date and compute number of days to
    # the target day.
    start = datetime.date(year, month, offset).weekday()
    target = day_lookup[day]
    offset += compute_offset(start, target)

    return datetime.date(year, month, offset)
