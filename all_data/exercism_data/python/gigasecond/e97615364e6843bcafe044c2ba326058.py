from datetime import timedelta

_giga_delta = timedelta(seconds=10 ** 9)


def add_gigasecond(d):
    return d + _giga_delta
