"""
This module takes "meetup-style" phrases paired with a month and a year
and returns the correct date on which this meetup should occur.
"""

import datetime


def meetup_day(year, month, day_of_week, qualifier):
    """
    This function takes a "meetup-style" phrase paired with a month and a
    year and returns the correct date on which this meetup should occur.
    """

    weekday_map = {"Monday": 0, "Tuesday": 1, "Wednesday": 2,
                   "Thursday": 3, "Friday": 4, "Saturday": 5, "Sunday": 6}

    qualifier_map = {'teenth': [13, 14, 15, 16, 17, 18, 19],
                     '1st': [1, 2, 3, 4, 5, 6, 7],
                     '2nd': [8, 9, 10, 11, 12, 13, 14],
                     '3rd': [15, 16, 17, 18, 19, 20, 21],
                     '4th': [22, 23, 24, 25, 26, 27, 28],
                     'last': [31, 30, 29, 28, 27, 26, 25, 24, 23, 22]}

    for day in qualifier_map[qualifier]:
        try:
            date_object = datetime.date(year, month, day)
            if date_object.weekday() == weekday_map[day_of_week]:
                return date_object
        except ValueError:
            continue
