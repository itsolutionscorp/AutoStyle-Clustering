import datetime


def add_gigasecond(date):
    return date + datetime.timedelta(seconds=1000000000)
