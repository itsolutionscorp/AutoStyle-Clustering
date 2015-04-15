import datetime as dt


def add_gigasecond(birthday):
    return birthday + dt.timedelta(seconds=1e9)
