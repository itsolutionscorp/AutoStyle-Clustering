__author__ = 'agupt15'

from datetime import timedelta


def add_gigasecond(dt):
    return dt + timedelta(seconds=1000000000)
