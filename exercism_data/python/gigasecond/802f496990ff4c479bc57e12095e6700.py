import datetime


def add_gigasecond(date):
    return date + datetime.timedelta(seconds=pow(10, 9))
