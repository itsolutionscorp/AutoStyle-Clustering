__author__ = 'matt'
#1 * 10**9
from datetime import datetime, date, timedelta


def add_gigasecond(date_in):
    date_in_as_datetime = datetime(date_in.year, date_in.month, date_in.day)
    return_date = date_in_as_datetime + timedelta(seconds=10**9)
    return date(return_date.year, return_date.month, return_date.day)
