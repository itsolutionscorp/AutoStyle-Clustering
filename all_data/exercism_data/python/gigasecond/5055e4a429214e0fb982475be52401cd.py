from datetime import timedelta


def add_gigasecond(birth_date):
    delta = timedelta(seconds=10**9)
    return birth_date + delta
