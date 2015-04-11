__author__ = 'linda'

from datetime import timedelta

gigasecond = timedelta(seconds=10**9)

def add_gigasecond(date):
    return date + gigasecond
