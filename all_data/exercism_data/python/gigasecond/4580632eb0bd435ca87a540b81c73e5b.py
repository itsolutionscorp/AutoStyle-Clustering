from datetime import timedelta


GIGASECOND = timedelta(seconds=10**9)


def add_gigasecond(d):
    return d + GIGASECOND