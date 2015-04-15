from datetime import date, timedelta

GIGASECOND = timedelta(10**9/(60*60*24))

def add_gigasecond(date):
    return date + GIGASECOND
