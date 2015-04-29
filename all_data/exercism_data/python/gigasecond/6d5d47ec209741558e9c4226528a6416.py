__author__ = 'Dalton'
import datetime


def add_gigasecond(first_date):
    gigasecond = datetime.timedelta(seconds=10**9)
    answer = first_date + gigasecond
    return answer
