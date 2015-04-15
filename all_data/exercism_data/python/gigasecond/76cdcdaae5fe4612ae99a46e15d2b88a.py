__author__ = 'grdunn'
import datetime


def add_gigasecond(start=None):
    """


    :rtype : date
    :param start:
    """

    return start + datetime.timedelta(seconds=10**9)
