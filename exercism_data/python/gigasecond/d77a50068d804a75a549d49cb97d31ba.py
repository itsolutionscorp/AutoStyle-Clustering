from datetime import timedelta

ONE_GIGASECOND = timedelta(seconds=1e9)


def add_gigasecond(d):
    return d + ONE_GIGASECOND
