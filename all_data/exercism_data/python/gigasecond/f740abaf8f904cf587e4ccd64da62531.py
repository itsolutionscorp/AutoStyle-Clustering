__author__ = 'James'

from datetime import date, timedelta


def add_gigasecond(start):
    """
    Take a given date and add a gigasecond, 10**9 seconds.
    :type start: date
    :param start: Start Date
    :return: Date with gigasecond added.
    """

    delta = timedelta(seconds=10**9)

    return start + delta
