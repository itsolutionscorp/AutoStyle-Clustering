import calendar
from datetime import datetime

__author__ = 'jetties'


def add_gigasecond(date):
    """
    Adds a gigasecond to the given datetime.
    :param date: A datetime.
    :return: A datetime with the given date with a gigasecond.
    """
    epoch = calendar.timegm(date.timetuple())
    date_plus_gigasecond = epoch + (10 ** 9)
    return datetime.utcfromtimestamp(date_plus_gigasecond)
