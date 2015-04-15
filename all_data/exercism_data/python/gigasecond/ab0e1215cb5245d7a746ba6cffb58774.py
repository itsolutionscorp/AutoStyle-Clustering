__author__ = 'romleinster'
import datetime
from datetime import date


def add_gigasecond(input_date):
    delta = input_date - date(1970, 1, 1)
    seconds_since = delta.total_seconds()
    gigaseconds = seconds_since + 1000000000
    return datetime.datetime.utcfromtimestamp(gigaseconds).date()
