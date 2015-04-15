__author__ = 'djdick'
from datetime import datetime, timedelta

def add_gigasecond(date):
    date += timedelta(seconds=10**9)
    return date
