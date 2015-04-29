from datetime import date
from datetime import timedelta


def add_gigasecond(dt):
    return dt + timedelta(0, 10**9)
