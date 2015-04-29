import datetime


def add_gigasecond(day):
    return day + datetime.timedelta(seconds=10**9)
