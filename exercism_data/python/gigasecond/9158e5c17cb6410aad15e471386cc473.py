import datetime


def add_gigasecond(time):
    return time + datetime.timedelta(seconds=10**9)
