from datetime import timedelta


def add_gigasecond(indate):
    return indate + timedelta(seconds=10**9)
