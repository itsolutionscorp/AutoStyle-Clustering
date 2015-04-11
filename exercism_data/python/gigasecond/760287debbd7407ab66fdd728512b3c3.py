import datetime


def add_gigasecond(date):
    gigasecond = 10**9
    return date + datetime.timedelta(seconds=gigasecond)
