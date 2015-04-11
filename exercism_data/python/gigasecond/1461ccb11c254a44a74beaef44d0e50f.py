__author__ = 'shandr'

from datetime import timedelta

def add_gigasecond(birth):
    gigasecond = 10**9
    gigasecond_delta = timedelta(seconds = gigasecond)
    return birth + gigasecond_delta
