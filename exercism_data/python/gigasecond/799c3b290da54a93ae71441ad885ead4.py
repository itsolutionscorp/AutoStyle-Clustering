__author__ = 'cameron'

from datetime import timedelta


def add_gigasecond(mydate):
    mydate += timedelta(seconds=10**9)
    return mydate
