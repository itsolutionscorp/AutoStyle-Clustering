"""Meetup"""

import calendar

# ``date.weekday()`` returns 0 for Monday and 6 for Sunday.
WEEKDAY = ("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
           "Saturday", "Sunday")

ORDINAL = ("1st", "2nd", "3rd", "4th")
LAST = "last"
TEENTH = "teenth"               # thirteenth, fourteenth, ..., nineteenth


def meetup_day(year, month, weekday, ordinal):
    """Return the date of a meetup."""
    nweekday = WEEKDAY.index(weekday)
    dates = [
        date
        for date in calendar.Calendar().itermonthdates(year, month)
        if date.month == month and date.weekday() == nweekday
    ]
    if ordinal in ORDINAL:
        return dates[ORDINAL.index(ordinal)]
    elif ordinal == LAST:
        return dates[-1]
    elif ordinal == TEENTH:
        return next(date for date in dates if 13 <= date.day <= 19)
    raise ValueError(year, month, weekday, ordinal)
