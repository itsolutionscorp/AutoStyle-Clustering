from datetime import timedelta

GIGA = timedelta(seconds=10**9)

def add_gigasecond(date):
    return date + GIGA
