__author__ = 'cameron'

from datetime import timedelta

def add_gigasecond(mydate):
    return mydate + timedelta(seconds=10**9)
