import datetime


def add_gigasecond(date):
    return date + datetime.timedelta(0, 10**9)
