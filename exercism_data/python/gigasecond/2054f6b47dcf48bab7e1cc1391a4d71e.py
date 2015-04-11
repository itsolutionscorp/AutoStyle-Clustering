__author__ = 'flux'
from datetime import date, timedelta

def add_gigasecond(birthday):
    gigasecInc=timedelta(seconds=10**9)
    return birthday + gigasecInc
