from datetime import date
from datetime import datetime
from datetime import timedelta

def add_gigasecond(date0):
    return date0 + timedelta(seconds=10**9)
