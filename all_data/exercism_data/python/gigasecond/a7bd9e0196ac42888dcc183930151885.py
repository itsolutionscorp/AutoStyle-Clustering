__author__ = 'Dalton'
import datetime


def add_gigasecond(first_date):
    return first_date + datetime.timedelta(seconds=10**9)
