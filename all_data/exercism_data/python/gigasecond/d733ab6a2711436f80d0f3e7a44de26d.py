from datetime import timedelta, datetime

GIGA = 10**9

def add_gigasecond(start_date):
    return start_date + timedelta(seconds=GIGA)
