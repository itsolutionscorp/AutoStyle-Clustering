__author__ = 'jetties'

from datetime import datetime
import calendar

WEEKDAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

TARGETS = {'teenth': 13,
           '1st': 1,
           '2nd': 8,
           '3rd': 15,
           '4th': 22,
           '5th': 29}


def meetup_day(year, month, day_name, target):
    """
    Calculate the date of meetups.

    :param year: Year of the meetup
    :param month: Month of the meetup
    :param day_name: 'Monday' ... 'Sunday'
    :param target_occurance: '1st' - '5th', 'teenth' or 'last'
    :return:
    """
    # Start with the first day of the given month, unless we're looking for the 'last' occurance
    start_day = TARGETS[target] if target != 'last' else calendar.monthrange(year, month)[1]
    dt = datetime(year, month, start_day)
    while (dt.weekday() != WEEKDAYS.index(day_name)):
        start_day += -1 if target == 'last' else 1
        dt = datetime(year, month, start_day)
    return dt.date()
