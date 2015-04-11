from datetime import date
from datetime import timedelta

def add_gigasecond(d):
    gs = timedelta(seconds=10**9)
    return d + gs
