from datetime import timedelta


def add_gigasecond(Date):
    return Date + timedelta(seconds=10 ** 9)
