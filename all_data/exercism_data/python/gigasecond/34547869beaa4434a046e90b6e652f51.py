from datetime import timedelta

GIGASECOND = 10 ** 9


def add_gigasecond(d):
    return d + timedelta(seconds=GIGASECOND)
