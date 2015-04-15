"""
This module takes "meetup-style" phrases paired with a
month and a year and returns the correct date on which
this meetup should occur.
"""

from datetime import date


def meetup_day(year, month, day_of_week, qualifier):
    """
    This function takes a "meetup-style" phrase paired
    with a month and a year and returns the correct
    date on which this meetup should occur.
    """

    weekdays = ["Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday",
                "Sunday"]

    qualifier_map = {'teenth': xrange(13, 20),
                     '1st': xrange(1, 8),
                     '2nd': xrange(8, 15),
                     '3rd': xrange(15, 22),
                     '4th': xrange(22, 29),
                     'last': reversed(xrange(22, 32))}

    for day in qualifier_map[qualifier]:
        try:
            date_object = date(year, month, day)
        except ValueError:
            continue
        if weekdays[date_object.weekday()] == day_of_week:
            return date_object
