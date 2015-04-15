from datetime import datetime
from datetime import timedelta

def add_gigasecond(d):
    return timedelta(seconds=10**9) + datetime(d.year, d.month, d.day, d.hour, d.minute, d.second)
