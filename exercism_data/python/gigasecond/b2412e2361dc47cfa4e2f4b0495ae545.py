from datetime import datetime
from datetime import timedelta

def add_gigasecond(d):
    return timedelta(seconds=10**9) + d
