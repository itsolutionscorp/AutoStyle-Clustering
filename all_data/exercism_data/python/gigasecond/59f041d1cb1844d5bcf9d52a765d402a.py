import datetime


GIGASECOND = datetime.timedelta(seconds=1e9)


def add_gigasecond(date):
    return date + GIGASECOND
