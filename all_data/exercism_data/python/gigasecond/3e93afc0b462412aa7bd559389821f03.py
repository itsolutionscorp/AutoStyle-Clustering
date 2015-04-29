from datetime import timedelta, datetime

def add_gigasecond(start_date):
    return start_date + timedelta(seconds=10**9)
