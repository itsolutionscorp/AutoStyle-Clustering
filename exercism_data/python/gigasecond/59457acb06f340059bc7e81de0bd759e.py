__author__ = 'Dalton'
import datetime


def add_gigasecond(first_date):
    gigasecond = datetime.timedelta(days=11574.0741)
    answer = first_date + gigasecond
    return answer
