__author__ = 'matt'

from datetime import datetime, date, timedelta


def add_gigasecond(date_in):
    return date_in + timedelta(seconds=10**9)
