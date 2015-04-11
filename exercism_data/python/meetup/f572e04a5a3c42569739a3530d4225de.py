#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from calendar import Calendar
from datetime import date

# Submission file for the python meetup_test exercise.
#
# v1: Use a calender.Calendar() to filter a list of (day_number,
#     week_day_number) tuples for the wanted conditions via list
#     comprehensions.

ords = ['1st', '2nd', '3rd', '4th', 'last']
teenth = range(13, 20)
weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
            'Saturday', 'Sunday']

cal = Calendar()


def cardinal(key):
    """
    Return the cardinal number of a given ordinal between '1st' (0)
    across '3rd' and '4th' up to 'last' (5).
    """
    return ords.index(key)


def meetup_day(year, month, weekday, ordinal):
    """
    Return a date for a given textural description of a meetup day.

    Meetup days are days that happen on the same day of the week every
    month. Examples for possible meetup days are

    - 'the 1st Monday'
    - 'the 3rd Tuesday'
    - 'the Wednesteenth' (the first Wednesday that ends in '-teenth')
    - 'the last Thursday'
    """

    wkday_idx = weekdays.index(weekday)

    # A list of possible dates with the wanted weekday in the given
    # month.
    # cal.itermonthdays2 returns (day_number, week_day_number) tuples.
    # It includes days from the previous month (day_number == 0), so we
    # filter these out.
    days = [d[0] for d in cal.itermonthdays2(year, month)
            if d[0] != 0 and d[1] == wkday_idx]

    if ordinal == 'teenth':
        # There are exactly 7 days that end in '-teenth'. Therefore,
        # it es guaranteed that each day of the week will have exactly
        # one '-teenth' in every month.
        day = [x for x in days if x in teenth][0]

    else:
        # get the day for the wanted cardinal
        day = days[cardinal(ordinal)]

    meetup_day = date(year, month, day)
    return meetup_day
