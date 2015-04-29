from datetime import timedelta, datetime

def add_gigasecond(date):
    giga = timedelta(seconds=10**9)
    return date + giga
