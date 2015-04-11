__author__ = 'jeffmarkey'
import datetime

def add_gigasecond(start_date):
    delta = datetime.timedelta(seconds = 10**9)
    new_date = start_date + delta
    return new_date
