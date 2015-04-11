from datetime import timedelta

GIGA = 10**9

def add_gigasecond(some_date):
    return some_date + timedelta(seconds=GIGA)
